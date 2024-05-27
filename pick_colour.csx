Random random = new();
var colourCsv = File.ReadAllLines("colours.csv");
var colours = colourCsv.Skip(1).Select(line =>
    new Colour
    {
        Name = line.Split(',')[0],
        R = int.Parse(line.Split(',')[1]),
        G = int.Parse(line.Split(',')[2]),
        B = int.Parse(line.Split(',')[3]),
        Selected = bool.Parse(line.Split(',')[4]),
    }).ToList();

Colour selectedColour;

do
{
    selectedColour = colours[random.Next(0, colours.Count)];
} while (selectedColour.Selected);

foreach (var colour in colours)
{
    colour.Selected = false;
}

selectedColour.Selected = true;

StringBuilder newCsv = new();
newCsv.AppendLine(colourCsv[0]);

foreach (var colour in colours)
{
    newCsv.AppendLine($"{colour.Name},{colour.R},{colour.G},{colour.B},{colour.Selected}");
}

File.WriteAllText("colours.csv", newCsv.ToString());

WriteLine(selectedColour);

record Colour
{
    public string Name { get; set; }
    public int R { get; set; }
    public int G { get; set; }
    public int B { get; set; }
    public bool Selected { get; set; }
}
