package com.example.firstaidapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager {
    private List<Question> questionList;
    private Random random;

    public QuestionManager() {
        questionList = new ArrayList<>();
        random = new Random();
        // Add your questions to the questionList
        questionList.add(new Question(1, 1, "Is direct pressure applied to a wound to control bleeding?", true));
        questionList.add(new Question(2, 1, "Should you remove an embedded object from a wound?", false));
        questionList.add(new Question(3, 1, "Is it necessary to perform rescue breaths in CPR?", true));

        // Level 2 questions
        questionList.add(new Question(4, 2, "Should you induce vomiting if someone ingests a harmful substance?", false));
        questionList.add(new Question(5, 2, "Is applying heat recommended for a burn injury?", false));
        questionList.add(new Question(6, 2, "Should you give water to a person experiencing a heat stroke?", false));
        questionList.add(new Question(4, 2, "What is the recommended first aid treatment for a sprained ankle?", false));
        questionList.add(new Question(5, 2, "Is it safe to induce vomiting for someone who has ingested a toxic substance?", false));
        questionList.add(new Question(6, 2, "What is the correct hand placement for performing chest compressions in CPR?", true));

        questionList.add(new Question(7, 3, "Should you apply ice or heat to a burn injury?", false));
        questionList.add(new Question(8, 3, "What is the proper technique for cleaning a small cut or scrape?", true));
        questionList.add(new Question(9, 3, "Can you use a tourniquet to control severe bleeding?", true));

    }

    public Question getRandomQuestion() {
        if (questionList.isEmpty()) {
            return null;
        }
        int index = random.nextInt(questionList.size());
        return questionList.get(index);
    }
}
