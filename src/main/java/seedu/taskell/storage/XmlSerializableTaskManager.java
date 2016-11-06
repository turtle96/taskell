package seedu.taskell.storage;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.events.storage.DuplicateDataExceptionEvent;
import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.model.ReadOnlyTaskManager;
import seedu.taskell.model.tag.Tag;
import seedu.taskell.model.tag.UniqueTagList;
import seedu.taskell.model.task.ReadOnlyTask;
import seedu.taskell.model.task.UniqueTaskList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * An Immutable TaskManager that is serializable to XML format
 */
@XmlRootElement(name = "taskmanager")
public class XmlSerializableTaskManager implements ReadOnlyTaskManager {
    
    private static final Logger logger = LogsCenter.getLogger(EventsCenter.class);

    @XmlElement
    private List<XmlAdaptedTask> tasks;
    @XmlElement
    private List<Tag> tags;

    {
        tasks = new ArrayList<>();
        tags = new ArrayList<>();
    }

    /**
     * Empty constructor required for marshalling
     */
    public XmlSerializableTaskManager() {}

    /**
     * Conversion
     */
    public XmlSerializableTaskManager(ReadOnlyTaskManager src) {
        tasks.addAll(src.getTaskList().stream().map(XmlAdaptedTask::new).collect(Collectors.toList()));
        tags = src.getTagList();
    }

    //@@author A0139257X-reused
    @SuppressWarnings("unchecked")
    @Override
    public UniqueTagList getUniqueTagList() {
        try {
            return new UniqueTagList(tags);
        } catch (UniqueTagList.DuplicateTagException e) {
            EventsCenter.getInstance().post(new DuplicateDataExceptionEvent(e));
            logger.info("Duplicated tags will be removed from UniqueTagList upon adding a new task");
            return new UniqueTagList((Set<Tag>)CollectionUtil.generateUniqueList(tags));
        }
    }

    @Override
    public UniqueTaskList getUniqueTaskList() {
        UniqueTaskList lists = new UniqueTaskList();
        for (XmlAdaptedTask p : tasks) {
            try {
                lists.add(p.toModelType());
            } catch (IllegalValueException e) {
                EventsCenter.getInstance().post(new DuplicateDataExceptionEvent(e));
                logger.info("Duplicated task will be removed upon adding a new task");
            }
        }
        return lists;
    }
  //@@author

    @Override
    public List<ReadOnlyTask> getTaskList() {
        return tasks.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                //TODO: better error handling
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Tag> getTagList() {
        return Collections.unmodifiableList(tags);
    }

}
