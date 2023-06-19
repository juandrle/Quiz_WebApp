import { ref, readonly } from "vue";
import type { IQuizInfo } from "./backendapitypen";
import { useInfo } from "./InfoService";
import { Client } from "@stomp/stompjs";
import type { IFrontendNachrichtEvent } from "./backendapitypen";

const quizinfoliste = ref<IQuizInfo[]>([]);

const { setHeader, setInfo } = useInfo()

const wsurl = `ws://${window.location.host}/stompbroker`
const dest = "/topic/quiz"

const stompclient = new Client({ brokerURL: wsurl })

export function useQuizService() {
    async function updateQuizInfoListe(): Promise<void> {
        try {
            const url = "/api/quiz"

            const response = await fetch(url)

            if (!response.ok) throw new Error(response.statusText)
            const jsondata: IQuizInfo[] = await response.json()

            quizinfoliste.value = jsondata
        } catch (err) {
            console.error(err)
            setHeader("Oops")
            setInfo(err as string)
        }
    }

    function startQuizLiveUpdate() {
        stompclient.onWebSocketError = (event) => { setHeader("Oops"), setInfo(event) }
        stompclient.onStompError = (frame) => { setHeader("Oops"), setInfo(frame.body) }

        stompclient.onConnect = (frame) => {
            stompclient.subscribe(dest, (message) => {
                const event: IFrontendNachrichtEvent = JSON.parse(message.body)

                if (event.eventTyp == "QUIZ") {
                    console.log("Received: " + event.operationTyp, event.eventTyp, event.eventID)
                    updateQuizInfoListe()
                }
            })
        }

        stompclient.activate()

        stompclient.onDisconnect = () => { }
    }

    return {
        quizinfoliste: readonly(quizinfoliste),
        updateQuizInfoListe,
        startQuizLiveUpdate
    }
}