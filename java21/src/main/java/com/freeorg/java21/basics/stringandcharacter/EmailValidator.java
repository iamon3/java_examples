package com.freeorg.java21.basics.stringandcharacter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class EmailValidator {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static void main(String[] args) {
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.processEmailList();
    }

    void processEmailList() {
        System.out.println("==== Invalid Emails =====");
        Set<String> invalidEmails = fromInvalidList.lines()
                .map(s -> {
                    System.out.println(s.substring(0, s.indexOf(',')).trim());
                    return s.substring(0, s.indexOf(',')).trim();
                })
                .collect(toSet());
        System.out.println("Invalid email = " + invalidEmails.size());
        try {
            // Collect all emails from all three lists, extract, validate, and format
            String emailContent = Stream.of(from2011List.lines(), from2013List.lines(), from2017List.lines())
                    .flatMap(s -> s)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(s -> {
                        int start = s.indexOf('<');
                        int end = s.indexOf('>');
                        if (start != -1 && end != -1 && start < end) {
                            return s.substring(start + 1, end).trim();
                        }
                        return s.trim();
                    })
                    .filter(this::isValidEmail)
                    .distinct()
                    .filter(email -> !invalidEmails.contains(email))  // Exclude invalid emails
                    .sorted()
                    .map(email -> email + ",")  // Add comma after each email
                    .collect(joining("\n"));    // Join with newlines
            
            // Write to file
            String outputPath = "emails.txt";
            Files.writeString(Paths.get(outputPath), emailContent);
            System.out.println("Emails successfully written to: " + outputPath);
            
        } catch (IOException e) {
            System.err.println("Error writing emails to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
     String from2011List = """
            Simon Trolly <s.t.sini105@gmail.com>,
            Mike Ray <mikeray@gmail.com>,
            good_bad_uglye@yahoo.com           
            """;

     String from2013List = """
            Brian Weisely <109brian@gmail.com>,
            Jackie Chan <chanjacky9824@gmail.com>,
            Simon Trolly <s.t.sini105@gmail.com>,
            luise.micky@mihpolland.com        
            """;

     String from2017List = """
            /johny.pep@gmail.com" </johny.pep@gmail.com>,
            68 batch <pulld_68@yahoogroups.com>,
            A Hussaini Brody <hussaini_k@usnl.com>,
            u09120@cs.unipurdu.ernet.in,
            Brian Weisely <109brian@gmail.com>
            """;

     String fromInvalidList = """
            good_bad_uglye@yahoo.com,
            weliketoplay@rocketmail.com,
            luise.micky@mihpolland.com,
            u09120@cs.unipurdu.ernet.in
            """;
}
