import java.util.Scanner;

public class LatexFormatter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Paste the unformatted LaTeX text below (press Enter twice to finish):");
        StringBuilder unformattedLatexBuilder = new StringBuilder();

        // Read input until an empty line is entered
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            unformattedLatexBuilder.append(line).append("\n");
        }

        String unformattedLatex = unformattedLatexBuilder.toString();

        String formattedLatex = formatLatex(unformattedLatex);
        System.out.println("\nFormatted LaTeX output:");
        System.out.println(formattedLatex);
    }

    private static String formatLatex(String unformattedLatex) {
        StringBuilder formattedLatex = new StringBuilder();

        // Split the unformatted LaTeX text by newlines
        String[] lines = unformattedLatex.split("\n");

        // Indentation count
        int indent = 0;

        // Iterate through each line
        for (String line : lines) {
            line = line.trim(); // Remove leading/trailing whitespace

            // Check if the line starts with section, bullet point, or conclusion marker
            if (line.startsWith("\\section") || line.startsWith("**Conclusion:**")) {
                formattedLatex.append("\n"); // Add new line before sections and conclusion
                formattedLatex.append(line).append("\n\n"); // Append the section or conclusion
                indent = 0; // Reset indentation
            } else if (line.startsWith("- **")) {
                formattedLatex.append("\t".repeat(Math.max(0, indent))); // Add indentation
                formattedLatex.append(line).append("\n"); // Append bullet points
                indent = 2; // Increase indentation for bullet points
            } else {
                formattedLatex.append("\t".repeat(Math.max(0, indent))); // Add indentation
                formattedLatex.append(line).append("\n"); // Append regular text
            }
        }

        return formattedLatex.toString();
    }
}
