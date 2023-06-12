package com.example.huynhdoanquanghuy.model

class CoffeOrder :java.io.Serializable{
    var oder_number:Int ? = 0
    var whipped :Boolean ? =false
    var chocolate :Boolean ? =false
    var quantity :Int ? =0
    var price : Double ? = 0.0
    var status : String? = ""

    constructor(order : Int ? , whippe : Boolean ?,chocolate : Boolean ? , quantity:Int  ? , status : String?){
        this.oder_number = order
        this.whipped = whipped
        this.chocolate = chocolate
        this.quantity = quantity
        this.status= status
        this.price = quantity!! * 4.0
        if (whippe == true) this.price = this.price!! + 0.5
        if (chocolate == true) this.price = this.price!! + 1

    }

}