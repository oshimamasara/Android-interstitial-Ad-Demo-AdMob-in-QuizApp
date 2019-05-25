package com.oshimamasara.myinterstitialdemo;


public class Question {

    public String questions[] = {
            "バスで子供が泣きやまない、ゴネる場合は？",
            "赤ちゃんがミルクを飲まない時は？",
    };

    public String choices[][] = {
            {"ほっとく", "怒る", "おもちゃ、パン、おにぎり、おかし、立って抱っこ、窓あける、最悪はバスを降りるなどの対策"},
            {"飲ますのを止める", "無理やり飲まそうとする", "ミルクの温度、周りの環境、赤ちゃんの体制などを確認して、最悪はムリに飲ませるのを止める"},
    };

    public String correctAnswer[] = {
            "おもちゃ、パン、おにぎり、おかし、立って抱っこ、窓あける、最悪はバスを降りるなどの対策",
            "ミルクの温度、周りの環境、赤ちゃんの体制などを確認して、最悪はムリに飲ませるのを止める",
    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }
}
