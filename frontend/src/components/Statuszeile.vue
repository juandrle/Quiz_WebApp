
<template>
    <div v-if="state.infoheader !== '' && state.infonachricht !== ''" class="alert alert-success fade show text-center">
        <h4 class="alert-heading">{{ state.infoheader }}</h4>
        <p>{{ state.infonachricht }}</p>
        <hr>
        <button type="button" class="btn-close me-auto" @click="setHeader(''), setInfo('')" />
    </div>
</template>

<script setup lang="ts">
import { useInfo } from '@/services/InfoService';

const { state, setHeader, setInfo } = useInfo()

</script>