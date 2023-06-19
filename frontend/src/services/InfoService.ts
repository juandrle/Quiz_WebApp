import { reactive } from 'vue'

// reactive() can only declare state for objects or arrays
// ref() for primitives
const state = reactive({
    infoheader: '',
    infonachricht: ''
})

export function useInfo() {
    function setHeader(header: string): void {
        state.infoheader = header
    }

    function setInfo(msg: string): void {
        state.infonachricht = msg
    }

    return {
        state: state,
        setHeader,
        setInfo
    }
}