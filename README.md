# Delta Generation Snippets
Code snippets to illustrate a method of delta generation.

## Comparing two lists of records
By comparing a list of new entries against a list of the entries last seen, it is possible to determine whether entries have been added, removed or modified. This project demonstrates how to compare a list of referenceKeys, valueKeys and values in order to determine deltas, where:
* A referenceKey is a unique identifier for an Entry
* A valueKey is an optional composite key which contains all of the values you are interested in comparing
* A value is the actual value of the entry

### Assumptions
This code example makes the following assumptions:
* The formula for a valueKey can not be changed or keyValue comparisons will not work as expected
* The valueKey is either always required for a comparison between two lists or never required, you can't begin using a valueKey and later decide not to provide one or comparisons will not work as expected
* The value of entries can be represented as a string

### Added entries
Added entries are the entries which have a referenceKey in the list of new entries but not in the list of previous entries

### Removed entries
Removed entries are the entries which have a referenceKey in the list of previous entries but not in the list of new entries

### Modified entries
Modified entries are the entries which have a referenceKey in the list of new entries and in the list of previous entries as well as having a valueKey that is different in both lists (if provided) or the value is different in both lists (if the valueKey is not provided)
