record Colour
{
    public string Name { get; init; }
    public string Hex { get; init; }
}

record History
{
    public string Date { get; init; }
    public Colour Colour { get; init; }
}

readonly string ChangelogPath = Path.Combine("ColourOfTheMonth", "app", "src", "main", "assets", "changelog.csv");
readonly string ColoursPath = Path.Combine("ColourOfTheMonth", "app", "src", "main", "assets", "colours.csv");

var colourCsv = File.ReadAllLines(ColoursPath);
var colours = colourCsv.Skip(1).Select(line =>
    new Colour
    {
        Name = line.Split(',')[0],
        Hex = line.Split(',')[1],
    }).ToList();
var colourDictionary = colours.ToDictionary(colour => colour.Name);

var changelogCsv = File.ReadAllLines(ChangelogPath);
var changelog = changelogCsv.Skip(1).Select(line =>
    new History
    {
        Date = line.Split(',')[0],
        Colour = colourDictionary[line.Split(',')[1]],
    }).ToList();
