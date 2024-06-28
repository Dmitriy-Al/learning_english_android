package aldmitry.dev.learningenglish.presenter

class LessonCreator {

    private val randomWords = listOf("I", "You", "he", "she", "they", "him", "her", "it", "them",
        "mine", "always", "our", "itself", "myself", "herself", "yourselves", "themselves",
        "ourselves", "himself", "those", "these", "that", "what", "which", "whose", "who", "whom",
        "me", "at", "to", "am", "go", "for", "in", "us", "be", "then", "if", "yes", "went",
        "are", "will", "want", "see", "take")


    fun createLesson(
        learningTypeSection: LearningTypeSection,
        lessonTexts: Map<String, String>,
        appLessonTexts: Map<String, String>
    ): List<LessonUnit> {
        val aggregatedTexts: MutableMap<String, String> = mutableMapOf()

        if (learningTypeSection == LearningTypeSection.UNITED_TEXTS) {
            aggregatedTexts.putAll(appLessonTexts)
            aggregatedTexts.putAll(lessonTexts)
        } else {
            aggregatedTexts.putAll(lessonTexts)
        }

        val lessonUnitList = mutableListOf<LessonUnit>()

        for ((ruText, enText) in aggregatedTexts) {
            val keyButtonsWords = mutableListOf<String>()

            if (enText.contains(" ")) {
                keyButtonsWords.addAll(enText.split(" "))
            } else {
                keyButtonsWords.add(enText)
            }

            for (i in keyButtonsWords.size until 10){
                val randomWord: String = randomWords[(randomWords.indices).random()]
                keyButtonsWords.add(randomWord)
            }
            keyButtonsWords.sortWith { a, b -> a.length - b.length }

            lessonUnitList.add(LessonUnit(ruText, enText, keyButtonsWords))
        }
        return lessonUnitList
    }


    fun createLesson(lessonTexts: Map<String, String>): List<LessonUnit> {
        val trainingTexts = mutableMapOf<String, String>()
        trainingTexts.putAll(lessonTexts)

        val lessonUnitList = mutableListOf<LessonUnit>()

        for ((ruText, enText) in trainingTexts) {
            val keyButtonsWords = mutableListOf<String>()

            if (enText.contains(" ")) {
                keyButtonsWords.addAll(enText.split(" "))
            } else {
                keyButtonsWords.add(enText)
            }

            for (i in keyButtonsWords.size until 10){
                val randomWord: String = randomWords[(randomWords.indices).random()]
                keyButtonsWords.add(randomWord)
            }
            keyButtonsWords.sortWith { a, b -> a.length - b.length }
            lessonUnitList.add(LessonUnit(ruText, enText, keyButtonsWords))
        }

        for ((ruText, enText) in trainingTexts) {
            lessonUnitList.add(LessonUnit(ruText, enText, mutableListOf()))
            lessonUnitList.add(LessonUnit(enText, ruText, mutableListOf()))
        }
        return lessonUnitList
    }

}


