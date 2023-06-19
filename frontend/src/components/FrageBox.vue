<template>
    <div class="card">
        <div class="card-body">
            <div class="row align-items-center">
                <div class="col-8">
                    <h6>{{ props.frage.fragetext }}</h6>
                </div>
                <div class="col text-end">
                    <h6>Punkt(e): {{ props.frage.punkte }}</h6>
                </div>
                <div class="col text-end">
                    <button type="button" @click.once="start" class="btn btn-px-150"
                        :class="timeLeft == 0 ? 'btn-secondary' : 'btn-primary'"
                        v-text="hasStarted ? (timeLeft == 0 ? 'Zeit vorbei' : `${timeLeft} Sekunden`) : 'Start'"
                        v-bind:disabled="timeLeft == 0" />
                </div>
            </div>
        </div>
        <div v-if="hasStarted" class="card-footer" :class="getStyleNoAnswer()">
            <div v-for="antwort in props.frage.alleAntworten " :key="antwort" :class="getStyleEvaluateAnswer(antwort)"
                class="col form-check">
                <input v-model="gewaehlteAntwort" :value="antwort" @change="fragebeantwortet()" :disabled="inputDisabled"
                    type="radio" class="form-check-input" />
                <label class="form-check-label">{{ antwort }}</label>
            </div>
            <div class="row mt-2">
                <caption v-if="keineAntwortGewaehlt">Es wurde keine Antwort ausgewählt.</caption>
            </div>
        </div>
        <div class="card-footer">
            <div v-if="hasStarted" class="progress" role="progressbar" :aria-valuenow="currProgress" :aria-valuemin="0"
                :aria-valuemax="100">
                <div class="progress-bar" :class="hasStarted ? 'bg-primary' : 'bg-secondary opacity-25'"
                    :style="'width: ' + `${currProgress}` + '%'"></div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import type { IFrage, IQuizErgebnis } from '@/services/backendapitypen';
import { computed, ref, watch } from 'vue';

const props = withDefaults(
    defineProps<{
        frage: IFrage,
        quizErgebnis: IQuizErgebnis,
        antwortzeit: number
    }>(), {
    antwortzeit: undefined
})

const antwortzeit = computed(() => {
    if (props.antwortzeit) {
        return props.antwortzeit
    }
    return props.frage.alleAntworten.length * 2
})

const hasStarted = ref(false)
const inputDisabled = ref(false)

const timeLeft = ref(antwortzeit.value)
const currProgress = ref(100)
const timerInterval = 1
const timerIntervalMs = timerInterval * 1000

const gewaehlteAntwort = ref("")

watch(gewaehlteAntwort, (newValue, oldValue) => {
    console.log(`Antwort[${props.frage.id}]: ${gewaehlteAntwort.value}`)
})

const emit = defineEmits<{
    fragebeantwortet: [frageid: number, antwort: string],
    zeitvorbei: [frageid: number]
}>()

function fragebeantwortet(): void {
    console.log(gewaehlteAntwort.value)
    emit("fragebeantwortet", props.frage.id, gewaehlteAntwort.value)
}

function zeitabgelaufen(): void {
    emit("zeitvorbei", props.frage.id)
}

const start = () => {
    hasStarted.value = true

    const timerid = setInterval(() => {
        timeLeft.value -= timerInterval
        currProgress.value = (timeLeft.value / antwortzeit.value) * 100

        if (timeLeft.value <= 0) {
            inputDisabled.value = true
            clearInterval(timerid)
            zeitabgelaufen()
        }
    }, timerIntervalMs)
}

const richtigBeantwortet = computed(() => {
    if (!props.quizErgebnis) {
        return undefined
    }
    return props.quizErgebnis.ergebnisse.find(e => e.fid == props.frage.id)?.richtig
})

function getStyleEvaluateAnswer(antwort: string): string {
    if (richtigBeantwortet.value == undefined || gewaehlteAntwort.value != antwort) {
        return ''
    }
    return richtigBeantwortet.value ? 'alert alert-success' : 'alert alert-danger'
}

const keineAntwortGewaehlt = ref(false)

function getStyleNoAnswer(): string {
    if (richtigBeantwortet.value != undefined && gewaehlteAntwort.value.length == 0) {
        keineAntwortGewaehlt.value = true
        return 'alert alert-danger'
    }
    return ''
}

</script>

<style scoped>
.btn-px-150 {
    width: 150px;
}

.bg-grey-25 {
    background-color: rgba(92, 99, 106, 0.1);
}
</style>