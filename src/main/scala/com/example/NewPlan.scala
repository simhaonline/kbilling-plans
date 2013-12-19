package com.example

import kbilling.types._

object NewPlan {

  object bones {
    
    def sum: Aggregate = aggregates.sum
    
  }

  object usd {

    def apply(`bones.sum`: BigDecimal): BigDecimal = {
      val normRate = BigDecimal(0.1)
      val discountRate = BigDecimal(0.05)
      val t = BigDecimal(4)

      `bones.sum` match {
        case x if x <= t => x * normRate
        case x if x > t => t * normRate + (x - t) * discountRate
      }
    }

  }

  def zeroBalance(`usd`: BigDecimal): Boolean = notifications.zero(`usd`)

}
