# A0142073Rreused
###### \java\seedu\taskell\model\ModelManager.java
``` java
    private class DateQualifier implements Qualifier {
        private Set<String> DateKeyWords;

        DateQualifier(Set<String> dateKeyWords) {
            this.DateKeyWords = dateKeyWords;
        }

        @Override
        public boolean run(ReadOnlyTask task) {
            String searchString = task.getStartDate().taskDate + " " + task.getTaskType();
            return DateKeyWords.stream().allMatch(keyword -> StringUtil.containsIgnoreCase(searchString, keyword));
        }

        @Override
        public String toString() {
            return "date = "  + String.join(", ", DateKeyWords);
        }
    }

    private class PriorityQualifier implements Qualifier {
        private Set<String> PriorityKeyWords;

        PriorityQualifier(Set<String> keyWords) {
            this.PriorityKeyWords = keyWords;
        }
        
        @Override
        public boolean run(ReadOnlyTask task) {
            String searchString = task.getTaskPriority().taskPriority;
            return PriorityKeyWords.stream().allMatch(keyword -> StringUtil.containsIgnoreCase(searchString, keyword));
        }


        @Override
        public String toString() {
            return "priority = " + String.join(", ", PriorityKeyWords);
        }

    }
```
