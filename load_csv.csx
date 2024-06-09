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

foreach (var path in Directory.GetFiles(Directory.GetCurrentDirectory()))
{
    Console.WriteLine(path); // full path
    Console.WriteLine(System.IO.Path.GetFileName(path)); // file name
}

const string ChangelogPath = @"ColourOfTheMonth\app\src\main\assets\changelog.csv";
const string ColoursPath = @"ColourOfTheMonth\app\src\main\assets\colours.csv";

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
