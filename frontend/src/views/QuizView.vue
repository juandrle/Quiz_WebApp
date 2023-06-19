<!-- 
:fragetext="..." instead of v-bind:fragetext="..."
you can also pass all the properties of object with just v-bind="..."
--> 
<template>
    <div class="mb-5">
        <h1>Quiz {{ props.quizid }}: {{ quiz!.name }} ({{ quiz?.gesamtpunkte }} Punkte)</h1>
    </div>
    <div class="d-grid gap-4">
        <FrageBox v-for="frage in quiz!.fragen" :key="frage.id" :frage="frage" :quizErgebnis="quizErgebnis"
            @fragebeantwortet="merkeBeantworteteFrage" @zeitvorbei.once="merkeAbgelaufeneFrage">
        </FrageBox>
    </div>
</template>



<script setup lang="ts">
import { ref, computed, onBeforeMount, watch } from 'vue';
import { useQuiz } from '@/services/QuizService'
import FrageBox from '@/components/FrageBox.vue'

const props = defineProps<{ quizid: string }>()
const { quiz, quizErgebnis, updateQuiz, checkQuiz } = useQuiz()

onBeforeMount(async () => {
    await updateQuiz(parseInt(props.quizid))
})

const beantworteteFragen = new Map<number, string>()
const abgelaufeneFragen = ref<number[]>([])

function merkeBeantworteteFrage(frageid: number, antwort: string): void {
    beantworteteFragen.set(frageid, antwort)
}

function merkeAbgelaufeneFrage(frageid: number) {
    if (!beantworteteFragen.has(frageid)) {
        // put empty string for not answered questions
        beantworteteFragen.set(frageid, "")
    }
    abgelaufeneFragen.value.push(frageid)
}

const quizFertig = computed(() => quiz.value?.fragen.length == abgelaufeneFragen.value.length)

watch(quizFertig, (newValue, oldValue) => {
    if (quizFertig.value) {
        checkQuiz(quiz.value!.id, beantworteteFragen)
    }
})

</script>