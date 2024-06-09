#load "load_csv.csx"

Random random = new();

Colour selectedColour;

if (changelog.Count == 0)
{
    selectedColour = colours[random.Next(0, colours.Count)];
}
else
{
    var lastColour = changelog[0].Colour;
    do
    {
        selectedColour = colours[random.Next(0, colours.Count)];
    } while (selectedColour == lastColour);
}

// Add days to the current time to avoid issues if script is run in different timezone
var releaseDate = $"{DateTime.Now.AddDays(2):MMMM yyyy}";

StringBuilder newCsv = new();
newCsv.AppendLine(changelogCsv[0]);
newCsv.AppendLine($"{releaseDate},{selectedColour.Name}");

foreach (var history in changelog)
{
    newCsv.AppendLine($"{history.Date},{history.Colour.Name}");
}

File.WriteAllText(ChangelogPath, newCsv.ToString());

WriteLine($"{releaseDate}: {selectedColour}");

var gitHubOutputPath = Environment.GetEnvironmentVariable("GITHUB_OUTPUT");

if (!string.IsNullOrEmpty(gitHubOutputPath))
{
    var colourOutput = $"selected_colour={selectedColour.Name}";
    var releaseDateOutput = $"release_date={releaseDate}";
    WriteLine($"Setting output: {colourOutput}");
    WriteLine($"Setting output: {releaseDateOutput}");
    File.AppendAllLines(gitHubOutputPath, [colourOutput, releaseDateOutput]);
}
