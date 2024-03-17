package aldmitry.dev.learningenglish.presenter

class Lesson(learningHandler: LearningHandler) {

    private val lessonCollector = learningHandler.lessonCollector
    private val randomWords = listOf("I", "You", "he", "she", "they", "him", "her", "it", "them", "mine", "always", "our", "itself", "myself", "herself", "yourselves", "themselves", "ourselves", "himself", "those", "these",
        "that", "what", "which", "whose", "who", "whom", "me", "at", "is", "to", "am", "go", "for", "in", "us", "be", "then", "if", "yes", "went", "are", "will", "want")

    var enText: String = ""
    var forTranslateText: String = ""
    val buttonText = mutableListOf<String>() // TODO будет ли var/очистка листа изменять урок

    fun receiveLesson() {
        forTranslateText = lessonCollector.keys.random()
        enText = lessonCollector.getValue(forTranslateText)
        buttonText.addAll(enText.split(" "))

        for(i in buttonText.size - 1 .. 8){
            val index = (randomWords.indices).random()
            buttonText.add(randomWords[index])
        }
        buttonText.sortWith { a, b -> a.length - b.length }
    }

}