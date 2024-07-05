package model

data class Student (
    var firstname: String,
    var lastname: String,
    var email: String,
    var grade: String,
    var phone: String
    )

val students: List<Student> = listOf(
    Student("John","Doe", "jeanne.doe@yahoo.uk", "AL 1", "90059173"),
    Student("Bélinda","Freitas", "belinda.freitas@gmail.com", "AL 4", "99989276"),
    Student("Jeanne","Doe", "john.doe@yahoo.fr", "SRS 1", "97574274"),
    Student("Xavier","Adédjé", "francois.xavier@outlook.fr", "MP 1", "90691179"),
    Student("Kristina","Tonteri-Young", "kty@gmail.com", "AL 3", "90919423"),
    Student("Gloria","Ferrari", "gloria.ferr@yahoo.fr", "AD 4", "93232014"),
    Student("Antoine","Doe", "tony.doe@gmail.com", "AL 2", "92405091"),
    Student("Alba","Baptista", "alba.baptista@info.pt", "IABD 5","99920228"),
    Student("Honoré","Adjé", "hono.adje@gmail.com", "AL 1","96626039"),
    Student("Marc","Adédjé", "marek@yahoo.fr", "SRS 3","70251488"),
    Student("Esther","Exposito", "esther_exp@yahoo.uk", "TL 2", "93397881"),
    Student("Innocent","Gafa", "innogafa@outlook.fr", "CCA 1", "98612148"),

)
