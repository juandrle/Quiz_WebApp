<template>
    <h1>Quiz Übersicht</h1>
    <p>Suchen Sie in der Liste und wählen Sie ein Quiz aus:</p>
    <div class="input-group">
        <input type="text" v-model="search" class="form-control">
        <button type="reset" @click="reset" class="btn btn-primary">Reset</button>
    </div>
    <hr>
    <table class="table table-hover caption-top">
        <caption v-if="search.length != 0">Suchergebnisse für "{{ search }}"</caption>
        <thead>
            <tr>
                <th class="col-9">Name</th>
                <th class="col-1">Fragen</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="quiz in filteredListe" :key="quiz.id">
                <td>
                    <div class="d-grid gap-2">
                        <RouterLink :to="{ path: '/quiz/' + quiz.id }" class="btn btn-transparent text-start">{{ quiz.name }}
                        </RouterLink>
                    </div>
                </td>
                <td>{{ quiz.anzahlFragen }} </td>
            </tr>
        </tbody>
    </table>
</template>

<script setup lang="ts">
import 'bootstrap/dist/css/bootstrap.min.css'
import { useQuizService } from '@/services/QuizListeService'
import { ref, computed, onMounted, watch } from 'vue';

const { quizinfoliste, updateQuizInfoListe, startQuizLiveUpdate } = useQuizService()

onMounted(async () => {
    await updateQuizInfoListe()
    startQuizLiveUpdate()
})

watch(quizinfoliste, (newValue, oldValue) => {
    // do something
})

const search = ref("")

const reset = () => {
    search.value = ""
}

const filteredListe = computed(() => {
    const searchQuery = search.value.toLocaleLowerCase().trim()
    if (!searchQuery) {
        return quizinfoliste.value
    } else {
        return quizinfoliste.value.filter((quiz) => quiz.name.toLocaleLowerCase().includes(searchQuery))
    }
})

</script>