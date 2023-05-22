import java.awt.*;

import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.util.Random;

/**
 * This tab allows the user to answer a succession of multiple choice or field-response questions 
 * from an included question bank. Feedback is given after each question answered.
 */
public class PracticeQuizTab extends QuestionTab 
{
    private JPanel answer;
    private JLabel answerText;
    private JLabel scoreText;
    private NormalButton answerButton;

    private JPanel questionContent;
    private JTextArea questionText;
    private JPanel answerContainer;
    private NormalButton[] integerSelectionButtons;
    private CardLayout answerSelector;

    //This switches between the question and answer interface
    private CardLayout flipLayout;

    private Question currentQuestion;
    /**
     * The number of correct answers given
     */
    private int score;

    /**
     * The number of total questions asked
     */
    private int total;

    /**
     * Initialize the PracticeQuizTab
     */
    public PracticeQuizTab()
    {
        super();
        
        // Initialize answer interstital
        answer = new JPanel();
        BoxLayout boxLayout = new BoxLayout(answer, BoxLayout.Y_AXIS);
        answer.setLayout(boxLayout);

        answerText = new JLabel();
        answer.add(answerText);

        scoreText = new JLabel();
        answer.add(scoreText);

        answerButton = new NormalButton("Next Question");
        answerButton.setClickHandler((var e) -> {
            nextQuestion();
        });
        answer.add(answerButton);

        //Initialize the question display
        questionContent = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(questionContent, BoxLayout.Y_AXIS);
        questionContent.setLayout(boxLayout2);
        
        questionText = new JTextArea();
        questionText.setLineWrap(true);
        questionText.setSize(200,200);

        questionText.setEditable(false);
        questionContent.add(questionText);


        answerContainer = new JPanel();
        answerSelector = new CardLayout();
        answerContainer.setLayout(answerSelector);

        //Initialize the boolean options card
        JPanel booleanOptionsDisplay = new JPanel();
        BoxLayout booleanOpBoxLayout  = new BoxLayout(booleanOptionsDisplay, BoxLayout.Y_AXIS);
        booleanOptionsDisplay.setLayout(booleanOpBoxLayout);

        NormalButton trueButton = new NormalButton("True");
        trueButton.setClickHandler((var e) -> {handleSelectAnswer(0);});
        booleanOptionsDisplay.add(trueButton);

        NormalButton falseButton = new NormalButton("False");
        falseButton.setClickHandler((var e) -> {handleSelectAnswer(1);});
        booleanOptionsDisplay.add(falseButton);

        //Initialize the integer multiple choice card
        JPanel integerOptionsDisplay = new JPanel();
        BoxLayout integerOpBoxLayout = new BoxLayout(integerOptionsDisplay, BoxLayout.Y_AXIS);
        integerOptionsDisplay.setLayout(integerOpBoxLayout);

        integerSelectionButtons = new NormalButton[4];

        //Setup the multiple-choice answer buttons
        for(int i = 0;i < 4; i++)
        {
            final int k = i;
            NormalButton btn = new NormalButton("");
            btn.setClickHandler((var e) -> {
                handleSelectAnswer(k);
            });

            integerOptionsDisplay.add(btn);
            integerSelectionButtons[i] = btn;
        }

    
        //Initialize the integer free response question card
        JPanel integerInput = new JPanel();
        BoxLayout intBoxLayout = new BoxLayout(integerInput,BoxLayout.Y_AXIS);
        integerInput.setLayout(intBoxLayout);
        
        final NormalTextField responseField = new NormalTextField();
        integerInput.add(responseField);

        NormalButton submit = new NormalButton("Submit");
    
        submit.setClickHandler((var e) -> {
            String text = responseField.getText();
            try {
                int val = Integer.parseInt(text);
                handleInputAnswer(val);
                responseField.setText("");
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Please enter an integer number");
            }
        });
        
        integerInput.add(submit);

        //Add the cards
        answerContainer.add(booleanOptionsDisplay,"boolean");
        answerContainer.add(integerOptionsDisplay,"integerOptions");
        answerContainer.add(integerInput,"integerInput");

        questionContent.add(answerContainer);
    
        //Initialize main component
        flipLayout = new CardLayout();

        component.setLayout(flipLayout);
        component.add(questionContent,"question");
        component.add(answer,"answer");
        
        //Load the first question for the user
        nextQuestion();        
    }

    //
    private void displayInterstital()
    {
        flipLayout.show(component,"answer");
        String scoreStr = String.format("Score %.2f %% (%d / %d)", score * 100.0 / total, score, total);
        scoreText.setText(scoreStr);
    }

    /**
     * called when the user gets an incorrect answer
     */
    private void incorrect()
    {

        //flipLayout.show(component,"answer");
        displayInterstital();
        answerText.setText("Incorrect");
    }

    /**
     * called when the user gets an answer correct
     */
    private void correct()
    {
        score += 1;
        displayInterstital();
        //flipLayout.show(component,"answer");
        answerText.setText("Correct");
    }

    private void handleInputAnswer(int answer)
    {
        if(answer == currentQuestion.corval)
        {
            correct();
        }
        else
        {
            incorrect();
        }
    }

    private void handleSelectAnswer(int clickIx)
    {
        //We show the correct interstital content when the user selects an answer  
        if(clickIx == currentQuestion.correct)
        {
            correct();
        }
        else{
            incorrect();
        }
    }

    private void nextQuestion() 
    {
        //Incremenet the number of questions that have been asked
        total += 1;

        Random r = new Random();
        currentQuestion = Question.getRandomQuestion();
        flipLayout.show(component, "question");

        
        questionText.setText(currentQuestion.question);
        

        switch(currentQuestion.questionType)
        {
            case Question.BOOLEAN:
                answerSelector.show(answerContainer,"boolean");
                break;
            case Question.NUMERIC:
                if(r.nextBoolean())
                {
                    for(int i = 0; i < currentQuestion.options.size();i++)
                    {
                        integerSelectionButtons[i].setText(
                            currentQuestion.options.get(i)
                        );

                        
                    }
                    System.out.print(currentQuestion.correct);
                    answerSelector.show(answerContainer,"integerOptions");
                }
                else{
                    answerSelector.show(answerContainer, "integerInput");
                }
        }
    }

}
