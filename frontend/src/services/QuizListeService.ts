import { reactive, readonly, ref } from "vue";
import type { IQuizInfo } from "./backendapitypen";

const quizinfoliste = ref<IQuizInfo[]>([
    { id: 1, name: "Anders", anzahlFragen: 5 },
    { id: 1, name: "Babylon", anzahlFragen: 6 },
    { id: 1, name: "Querharmonik", anzahlFragen: 7 }
]);

export function useQuizService() {
    return {
        quizinfoliste: readonly(quizinfoliste.value)
    }
}