
export interface IQuizInfo {
    id: number
    name: string
    anzahlFragen: number
}

export interface IQuiz {
    id: number
    name: string
    fragen: Array<IFrage>
    gesamtpunkte: number
}

export interface IFrage {
    id: number
    katname: string
    fragetext: string
    alleAntworten: Array<string>
    punkte: number
}