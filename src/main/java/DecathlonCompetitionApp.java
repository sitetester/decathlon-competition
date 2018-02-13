import Service.Helper.FileHelper;
import Service.InputFileParser;
import Service.Reader.InputFileFormatToReaderMapper;
import Service.Reader.InputFileReaderProvider;
import Service.Score.AthletePlaceOfCompetitionProvider;
import Service.Score.AthleteScoresCalculator;
import Service.Score.AthleteScoresSorter;
import Service.Writer.OutputFileFormatToWriterMapper;
import Service.Writer.OutputFileWriterInterface;
import Service.Writer.OutputFileWriterProvider;

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.Map;

public class DecathlonCompetitionApp {

    public static void main(String args[]) throws FileNotFoundException {

        /*String inputFilePath;
        String outputFilePath;

        if (args.length == 0) {
            Console c = System.console();
            if (c == null) {
                System.err.println("No console.");
                System.exit(1);
            }

            inputFilePath = c.readLine("Enter input file path: ");
            outputFilePath = c.readLine("Enter output file path: ");

        } else {
            inputFilePath = args[0];
            outputFilePath = args[1];
        }*/


        String inputFilePath = "/Users/khalid/IdeaProjects/app-decathlon-competition/src/main/resources/decathlon_input.txt";
        System.out.println("input file path is : " + inputFilePath);

        String outputFilePath = "/Users/khalid/IdeaProjects/app-decathlon-competition/src/main/resources/decathlon_output.xml";
        System.out.println("output file path is : " + outputFilePath);

        InputFileParser inputFileParser = new InputFileParser(
                new InputFileReaderProvider(new InputFileFormatToReaderMapper())
        );

        // get collection of event=>score for each athlete
        Map<String, Map<String, Float>> eventScoresByAthletes = inputFileParser.parseFile(inputFilePath);

        // calculate event score for each athlete by applying respective formula
        Map<String, Integer> athleteTotalEventsScoreResult = new AthleteScoresCalculator().calculate(eventScoresByAthletes);

        // sort athletes by the total score
        Map<String, Integer> athleteSortedScoreResult = AthleteScoresSorter.sort(
                athleteTotalEventsScoreResult
        );

        // calculate the "place" of each athlete based on sorted total score
        Map<String, String> placeOfCompetition = new AthletePlaceOfCompetitionProvider().providePlaceOfCompetition(athleteSortedScoreResult);


        // get writer based on output file format
        OutputFileWriterInterface outputFileWriter = new OutputFileWriterProvider(new OutputFileFormatToWriterMapper())
                .provideWriter(
                        FileHelper.getFileExtension(outputFilePath)
                );

        outputFileWriter.write(athleteSortedScoreResult, eventScoresByAthletes, placeOfCompetition, outputFilePath);
    }
}
