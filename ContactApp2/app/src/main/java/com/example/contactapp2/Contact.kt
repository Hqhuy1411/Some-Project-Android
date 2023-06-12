package com.example.contactapp2

class Contact {
    var name : String ? = ""
    var phone : String ? = ""
    var note : String ? = ""
    var url : String ? = ""

    constructor(name:String?,phone:String?,note:String?,url:String?){
        this.name=name
        this.note= note
        this.phone=phone
        this.url=url
    }
}