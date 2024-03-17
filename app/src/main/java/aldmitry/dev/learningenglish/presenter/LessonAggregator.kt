package aldmitry.dev.learningenglish.presenter

class LessonAggregator {

    fun buildLesson(learningTypeSection: LearningTypeSection, lessonTexts: Map<String, String>): Map<String, String> {
        val aggregatedTexts: MutableMap<String, String> = mutableMapOf()
        val ownTexts: MutableMap<String, String> = mutableMapOf("Я пойду домой" to "I will go home", "Я не пойду домой" to "I will not go home", "Я ходил домой" to "I went home")

        when(learningTypeSection){
            LearningTypeSection.ADD_TEXT -> {
                aggregatedTexts.putAll(lessonTexts)
            }
            LearningTypeSection.UNITED_TEXTS -> {
                aggregatedTexts.putAll(lessonTexts)
            }
            LearningTypeSection.USERS_TEXTS -> {
                aggregatedTexts.putAll(ownTexts)
            }
            LearningTypeSection.SHARE_TEXTS -> {
                aggregatedTexts["Тексты пользователей"] = "user's texts"
            }
        }
        return aggregatedTexts
    }


}