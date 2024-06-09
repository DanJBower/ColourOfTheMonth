const string VersionPath = @"ColourOfTheMonth\app\build.gradle.kts";
var lines = File.ReadAllLines(VersionPath);


DateTime now = DateTime.Now;
DateTime startOfYear = new DateTime(now.Year, 1, 1);
TimeSpan elapsed = now - startOfYear;

int versionCode = ((now.Year % 100) * 1_000_000) + (int)elapsed.TotalMinutes;

for (int i = 0; i < lines.Length; i++)
{
    var line = lines[i];

    if (line.Contains("versionCode"))
    {
        var newLine = $"        versionCode = {versionCode}";
        WriteLine(newLine.Trim());
        lines[i] = newLine;
    }
    else if (line.Contains("versionName"))
    {
        int index = line.LastIndexOf('.');
        var newLine = $"{line[..index]}.{versionCode}\"";
        WriteLine(newLine.Trim());
        lines[i] = newLine;
    }
}

File.WriteAllLines(VersionPath, lines);
