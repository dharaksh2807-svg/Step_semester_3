package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 4: Examination Question Grader
 * Evaluates MCQ, TF, and ESSAY questions polymorphically based on specific grading criteria.
 */
public class ExamQuestionGrader {

    public static abstract class ExamQuestion {
        private String type;
        private String questionText;
        private String correctAnswer;
        private String studentAnswer;
        private double points;

        public ExamQuestion(String type, String questionText, String correctAnswer, String studentAnswer, double points) {
            this.type = type;
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        public String getType() {
            return type;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String getStudentAnswer() {
            return studentAnswer;
        }

        public double getPoints() {
            return points;
        }

        public abstract double evaluateScore();
    }

    public static class MCQQuestion extends ExamQuestion {
        public MCQQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super("MCQ", questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public double evaluateScore() {
            return getStudentAnswer().trim().equals(getCorrectAnswer().trim()) ? getPoints() : 0.0;
        }
    }

    public static class TFQuestion extends ExamQuestion {
        public TFQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super("TF", questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public double evaluateScore() {
            return getStudentAnswer().trim().equalsIgnoreCase(getCorrectAnswer().trim()) ? getPoints() : 0.0;
        }
    }

    public static class EssayQuestion extends ExamQuestion {
        public EssayQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super("ESSAY", questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public double evaluateScore() {
            String[] keywords = getCorrectAnswer().split(",");
            String studentTextLower = getStudentAnswer().toLowerCase();
            int matchedKeywords = 0;

            for (String kw : keywords) {
                String cleanKw = kw.trim().toLowerCase();
                if (!cleanKw.isEmpty() && studentTextLower.contains(cleanKw)) {
                    matchedKeywords++;
                }
            }

            if (matchedKeywords >= 2) {
                return getPoints() * 0.75;
            } else if (matchedKeywords == 1) {
                return getPoints() * 0.50;
            } else {
                return 0.0;
            }
        }
    }

    public static ExamQuestion parseQuestion(String line) {
        List<String> tokens = new ArrayList<>();
        Matcher m = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line.trim());
        while (m.find()) {
            if (m.group(1) != null) {
                tokens.add(m.group(1));
            } else {
                tokens.add(m.group(2));
            }
        }

        String type = tokens.get(0).toUpperCase();
        String questionText = tokens.size() > 1 ? tokens.get(1) : "";
        String correctAnswer = tokens.size() > 2 ? tokens.get(2) : "";
        String studentAnswer = tokens.size() > 3 ? tokens.get(3) : "";
        double points = tokens.size() > 4 ? Double.parseDouble(tokens.get(4)) : 0.0;

        switch (type) {
            case "MCQ":
                return new MCQQuestion(questionText, correctAnswer, studentAnswer, points);
            case "TF":
                return new TFQuestion(questionText, correctAnswer, studentAnswer, points);
            case "ESSAY":
            default:
                return new EssayQuestion(questionText, correctAnswer, studentAnswer, points);
        }
    }

    public static void gradeQuestions(List<ExamQuestion> questions) {
        double overallScore = 0.0;
        for (ExamQuestion q : questions) {
            double score = q.evaluateScore();
            overallScore += score;
            System.out.printf("%s: %.2f%n", q.getType(), score);
        }
        System.out.printf("Total Score: %.2f%n", overallScore);
    }

    public static void main(String[] args) {
        try {
            if (System.in.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    int n = Integer.parseInt(line.trim());
                    List<ExamQuestion> questions = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String itemLine = reader.readLine();
                        if (itemLine == null) break;
                        questions.add(parseQuestion(itemLine));
                    }
                    gradeQuestions(questions);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<ExamQuestion> sample = new ArrayList<>();
        sample.add(new MCQQuestion("What is the capital of France?", "Paris", "Paris", 10));
        sample.add(new TFQuestion("The Earth is flat?", "False", "True", 5));
        sample.add(new EssayQuestion("Name two primary OOP principles.", "Inheritance, Polymorphism, Encapsulation", "Polymorphism is one.", 20));
        sample.add(new EssayQuestion("Describe abstraction and composition.", "Abstraction, Composition", "I talked about abstraction.", 15));
        gradeQuestions(sample);
    }
}
