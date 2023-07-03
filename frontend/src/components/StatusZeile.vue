
<template>
    <div v-if="state.infoheader !== '' && state.infonachricht !== ''"
        class="alert alert-danger alert-dismissible fade show shadow-sm mx-auto w-50 mt-3">
        <div>
            <h4 class="alert-header">{{ state.infoheader }}</h4>
            <p>{{ state.infonachricht }}</p>
        </div>
        <button type="button" class="btn-close" data-bs-dismiss="alert" @click="setHeader(''), setInfo('')" />
    </div>
</template>

<script setup lang="ts">
import { useInfo } from '@/services/InfoService';

const { state, setHeader, setInfo } = useInfo()

</script>