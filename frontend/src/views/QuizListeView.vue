<template>
    <h2>Quiz Übersicht</h2>
    <p>Suchen Sie in der Liste und wählen Sie aus diesen 3 Ihr Quiz aus:</p>
    <input type="text" v-model="input">
    <button @click="reset">Reset</button>
    <hr>
    <table>
        <tr>
            <th>Name</th>
            <th>Fragen</th>
        </tr>
        <tr v-for="quizinfo in filteredListe">
            <td><a v-bind:href="'/quiz/' + quizinfo.id">{{ quizinfo.name }}</a></td>
            <td>{{ quizinfo.anzahlFragen }} </td>
        </tr>
    </table>
</template>

<script setup lang="ts">
import 'bootstrap/dist/css/bootstrap.min.css'
import { useQuizService } from '@/services/QuizListeService'
import { computed, ref } from 'vue';

const { quizinfoliste } = useQuizService()
const input = ref("")

const reset = () => {
    input.value = ""
}

const filteredListe = computed(() => {
    const searchQuery = input.value.toLocaleLowerCase().trim()
    if (!searchQuery) {
        return quizinfoliste
    } else {
        return quizinfoliste.filter((quizinfo) => quizinfo.name.toLocaleLowerCase().includes(searchQuery))
    }
})

</script>