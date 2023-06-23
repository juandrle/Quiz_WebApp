
import { ref, readonly } from 'vue'
import type { IAntwort, IBeantwortetesQuiz, IQuiz, IQuizErgebnis } from './backendapitypen'
import { useInfo } from './InfoService'

const quiz = ref<IQuiz>()

const quizErgebnis = ref<IQuizErgebnis>()

const { setHeader, setInfo } = useInfo()

export function useQuiz() {
    async function updateQuiz(qid: number) {
        try {
            // use template string (needs backtick ` instead of " to work as intended)
            const url = `/api/quiz/${qid}`

            const response = await fetch(url)

            if (!response.ok) throw new Error(response.statusText)
            const jsondata: IQuiz = await response.json()

            quiz.value = jsondata
        } catch (err) {
            console.error(err)
            setHeader("Oops")
            setInfo(err as string)
        
        }
    }

    async function checkQuiz(qid: number, beantworteteFragen: Map<number, string>) {
        try {
            const url = "/api/quiz/check"

            const antworten = Array<IAntwort>()
            beantworteteFragen.forEach((v, k) => {
                const antwort: IAntwort = { fid: k, antwort: v }
                antworten.push(antwort)
            })

            const beantwortetesQuiz: IBeantwortetesQuiz = { qid: qid, antworten: antworten }

            const requestBody = JSON.stringify(beantwortetesQuiz)

            const response = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: requestBody,
            })

            if (!response.ok) throw new Error(response.statusText)
            const jsondata: IQuizErgebnis = await response.json()

            console.log(jsondata)
            quizErgebnis.value = jsondata
        } catch (err) {
            console.error(err)
            setHeader("Oops")
            setInfo(err as string)
            return null
        }
    }

    return {
        quiz: readonly(quiz),
        quizErgebnis: readonly(quizErgebnis),
        updateQuiz,
        checkQuiz
    }
}