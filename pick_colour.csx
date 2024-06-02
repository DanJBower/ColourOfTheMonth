Random random = new();
var colourCsv = File.ReadAllLines("colours.csv");
var colours = colourCsv.Skip(1).Select(line =>
    new Colour
    {
        Name = line.Split(',')[0],
        R = int.Parse(line.Split(',')[1]),
        G = int.Parse(line.Split(',')[2]),
        B = int.Parse(line.Split(',')[3]),
    }).ToList();
var colourDictionary = colours.ToDictionary(colour => colour.Name);

var changelogCsv = File.ReadAllLines("changelog.csv");
var changelog = changelogCsv.Skip(1).Select(line =>
    new History
    {
        Date = line.Split(',')[0],
        Colour = colourDictionary[line.Split(',')[1]],
    }).ToList();

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

File.WriteAllText("changelog.csv", newCsv.ToString());

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

record Colour
{
    public string Name { get; init; }
    public int R { get; init; }
    public int G { get; init; }
    public int B { get; init; }
}

record History
{
    public string Date { get; init; }
    public Colour Colour { get; init; }
}
