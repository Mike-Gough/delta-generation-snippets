package example

class EntriesComparator(previousEntries: Set[Entry], newEntries: Set[Entry]) {

  lazy val previousEntriesByRef: Map[String, Entry] =
  	previousEntries.map(e => (e.referenceKey, e)).toMap

  lazy val newEntriesByRef: Map[String, Entry] =
  	newEntries.map(e => (e.referenceKey, e)).toMap

  def getAddedEntries(): Set[Entry] = {
    newEntries.diff(previousEntries)
  }

  def getRemovedEntries(): Set[Entry] = {
    previousEntries.diff(newEntries)
  }

  def getModifiedEntries(): Set[Entry] = {
    val intersect = newEntries.intersect(previousEntries)
    intersect.filterNot(e => {
      val previousEntry = previousEntriesByRef.get(e.referenceKey).get
      val newEntry = newEntriesByRef.get(e.referenceKey).get
      previousEntry.valueEquals(newEntry)
    })
  }
}
