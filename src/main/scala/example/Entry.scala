package example

case class Entry(referenceKey: String, valueKey: Option[String], value: String) {
  // case classes should be considered equal when their reference keys are the same
  override def equals(o: Any) = o match {
    case that: Entry =>
      that.referenceKey.equalsIgnoreCase(this.referenceKey)
    case _ => false
  }
  def valueEquals(o:Any) = {
    println(this,o)
    o match {
    case Entry(thatReferenceKey,Some(thatValueKey), _) if !this.valueKey.isEmpty =>
    	thatReferenceKey.equalsIgnoreCase(this.referenceKey) &&
    		thatValueKey.equalsIgnoreCase(this.valueKey.get)
    case Entry(thatReferenceKey,None, thatValue) if this.valueKey.isEmpty =>
      thatReferenceKey.equalsIgnoreCase(this.referenceKey) &&
    		thatValue.equalsIgnoreCase(this.value)
    case _ => false
  }
}
  override def hashCode = ("%s".format(referenceKey)).hashCode
}
