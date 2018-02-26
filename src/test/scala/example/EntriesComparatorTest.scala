package example

import org.scalatest._

class EntriesComparatorTest extends FlatSpec with Matchers {

  "The EntriesComparator" should "detect added entries" in {
    val oldEntries: Set[Entry] = Set(Entry("A1", Some("A1Bob"), "id:A1,name:Bob"))
    val newEntries: Set[Entry] = Set(Entry("A1", Some("A1Smith"), "id:A1,name:Smith"),Entry("A2", Some("A1Bobbie"), "id:A1,name:Bobbie"))

    val comparator = new EntriesComparator(oldEntries,newEntries)
    val expectedEntries: Set[Entry] = Set(Entry("A2", Some("A1Bobbie"), "id:A1,name:Bobbie"))

    comparator.getAddedEntries() shouldEqual expectedEntries
  }

  "The EntriesComparator" should "detect removed entries" in {
    val oldEntries: Set[Entry] = Set(Entry("A1", Some("A1Bob"), "id:A1,name:Bob"))
    val newEntries: Set[Entry] = Set(Entry("A2", Some("A1Bobbie"), "id:A1,name:Bobbie"))

    val comparator = new EntriesComparator(oldEntries,newEntries)
    val expectedEntries: Set[Entry] = Set(Entry("A1", Some("A1Bob"), "id:A1,name:Bob"))

    comparator.getRemovedEntries() shouldEqual expectedEntries
  }

  "The EntriesComparator" should "detect modified entries based on a valueKey" in {
    val oldEntries: Set[Entry] = Set(Entry("A1", Some("A1Bob"), "id:A1,name:Bob"))
    val newEntries: Set[Entry] = Set(Entry("A1", Some("A1Bobbie"), "id:A1,name:Bobbie"))

    val comparator = new EntriesComparator(oldEntries,newEntries)
    val expectedEntries: Set[Entry] = Set(Entry("A1", Some("A1Bobbie"), "id:A1,name:Bobbie"))

    comparator.getModifiedEntries() shouldEqual expectedEntries
  }

  "The EntriesComparator" should "detect modified entries based on a value when no valueKey exists" in {
    val oldEntries: Set[Entry] = Set(Entry("A1", None, "id:A1,name:Bob"))
    val newEntries: Set[Entry] = Set(Entry("A1", None, "id:A1,name:Bobbie"))

    val comparator = new EntriesComparator(oldEntries,newEntries)
    val expectedEntries: Set[Entry] = Set(Entry("A1", None, "id:A1,name:Bobbie"))

    comparator.getModifiedEntries() shouldEqual expectedEntries
  }

  "The EntriesComparator" should "not detect changes when the valueKey has not changed" in {
    val oldEntries: Set[Entry] = Set(Entry("A1", Some("A1Bob"), "id:A1,name:Bob"))
    val newEntries: Set[Entry] = Set(Entry("A1", Some("A1Bob"), "id:A1,name:Bobbie"))

    val comparator = new EntriesComparator(oldEntries,newEntries)
    val expectedEntries: Set[Entry] = Set()

    comparator.getModifiedEntries() shouldEqual expectedEntries
  }

  "The EntriesComparator" should "not detect changes when the value has not changed and valueKey was not provided" in {
    val oldEntries: Set[Entry] = Set(Entry("A1", None, "id:A1,name:Bob"))
    val newEntries: Set[Entry] = Set(Entry("A1", None, "id:A1,name:Bob"))

    val comparator = new EntriesComparator(oldEntries,newEntries)
    val expectedEntries: Set[Entry] = Set()

    comparator.getModifiedEntries() shouldEqual expectedEntries
  }
}
