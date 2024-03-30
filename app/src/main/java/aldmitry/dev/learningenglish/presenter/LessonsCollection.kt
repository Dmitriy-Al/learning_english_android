package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.model.lessons.Compares
import aldmitry.dev.learningenglish.model.lessons.DateAndTime
import aldmitry.dev.learningenglish.model.lessons.MuchMany
import aldmitry.dev.learningenglish.model.lessons.PassiveVoice
import aldmitry.dev.learningenglish.model.lessons.PerfectTense
import aldmitry.dev.learningenglish.model.lessons.PresentContinuous
import aldmitry.dev.learningenglish.model.lessons.PresentSimple
import aldmitry.dev.learningenglish.model.lessons.VariousTexts
import aldmitry.dev.learningenglish.model.lessons.WordsForLearning

data class LessonsCollection(
    val allLessons: List<Learnable> = listOf(PresentContinuous(), PresentSimple(), PassiveVoice(),
        DateAndTime(), PerfectTense(), MuchMany(), Compares(), VariousTexts(), WordsForLearning()),
)




