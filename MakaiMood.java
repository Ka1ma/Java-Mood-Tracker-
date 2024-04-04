import java.util.ArrayList;
import java.util.Scanner;

class Mood {
    private String date;
    private int moodLevel;

    public Mood(String date, int moodLevel) {
        this.date = date;
        this.moodLevel = moodLevel;
    }

    public String getDate() {
        return date;
    }

    public int getMoodLevel() {
        return moodLevel;
    }
}

class MoodTracker {
    private ArrayList<Mood> moodEntries;

    public MoodTracker() {
        moodEntries = new ArrayList<>();
    }

    public void addMood(String date, int moodLevel) {
        Mood mood = new Mood(date, moodLevel);
        moodEntries.add(mood);
    }

    public void displayAllMoods() {
        for (Mood mood : moodEntries) {
            System.out.println("Date: " + mood.getDate() + ", Mood Level: " + mood.getMoodLevel());
        }
    }

    public double calculateAverageMood() {
        if (moodEntries.isEmpty())
            return 0;
        int totalMood = 0;
        for (Mood mood : moodEntries)
            totalMood += mood.getMoodLevel();
        return (double) totalMood / moodEntries.size();
    }

    public String findHappiestDay() {
        if (moodEntries.isEmpty())
            return "No mood entries";
        int maxMoodLevel = Integer.MIN_VALUE;
        String happiestDay = "";
        for (Mood mood : moodEntries)
            if (mood.getMoodLevel() > maxMoodLevel) {
                maxMoodLevel = mood.getMoodLevel();
                happiestDay = mood.getDate();
            }
        return happiestDay;
    }

    public String findSaddestDay() {
        if (moodEntries.isEmpty())
            return "No mood entries";
        int minMoodLevel = Integer.MAX_VALUE;
        String saddestDay = "";
        for (Mood mood : moodEntries)
            if (mood.getMoodLevel() < minMoodLevel) {
                minMoodLevel = mood.getMoodLevel();
                saddestDay = mood.getDate();
            }
        return saddestDay;
    }
}

public class MakaiMood {
    public static void main(String[] args) {
        MoodTracker moodTracker = new MoodTracker();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Makai Mood Tracker!");
        while (true) {
            System.out.print("Enter your mood level (1-10) or type 'exit' to quit: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit"))
                break;
            try {
                int moodLevel = Integer.parseInt(input);
                if (moodLevel < 1 || moodLevel > 10) {
                    System.out.println("Invalid mood level. Please enter a number between 1 and 10.");
                    continue;
                }
                String currentDate = java.time.LocalDate.now().toString();
                moodTracker.addMood(currentDate, moodLevel);
                System.out.println("Mood added successfully!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
            }
        }
        System.out.println("All Mood Entries:");
        moodTracker.displayAllMoods();
        System.out.println("Average Mood Level: " + moodTracker.calculateAverageMood());
        System.out.println("Happiest Day: " + moodTracker.findHappiestDay());
        System.out.println("Saddest Day: " + moodTracker.findSaddestDay());
        scanner.close();
    }
}