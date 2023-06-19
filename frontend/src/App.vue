
<template>
  <header>
    <nav class="navbar navbar-dark bg-primary shadow-sm">
      <RouterLink to="/" class="navbar-brand">Quizzler</RouterLink>
      <button class="btn btn-light" v-on:click="setHeader(header), setInfo(message)">Hallo</button>
    </nav>
    <StatusZeile></StatusZeile>
    <main class="container shadow mb-5">
      <RouterView />
    </main>
  </header>
</template>

<script setup lang="ts">
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import 'bootstrap'
import '../../src/main/resources/static/style.css'
import { RouterLink, RouterView } from 'vue-router'
import StatusZeile from '@/components/StatusZeile.vue'
import { useInfo } from '@/services/InfoService'

const { setHeader, setInfo } = useInfo()

const header = 'Well done!'
const message = 'Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.'

</script>

<style scoped></style>
