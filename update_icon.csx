#load "load_csv.csx"

using System.Xml.Linq;

const string IconPath = @"ColourOfTheMonth\app\src\main\res\drawable\icon.xml";

XDocument xmlDocument = XDocument.Load(IconPath);

XNamespace androidNs = "http://schemas.android.com/apk/res/android";

UpdateColour("current_month", changelog[0].Colour.Hex);
UpdateColour("one_month_ago", changelog[1].Colour.Hex);
UpdateColour("two_months_ago", changelog[2].Colour.Hex);
UpdateColour("three_months_ago", changelog[3].Colour.Hex);
UpdateColour("four_months_ago", changelog[4].Colour.Hex);

xmlDocument.Save(IconPath);

private void UpdateColour(string path, string colour)
{
    XElement pathElement = xmlDocument.Descendants()
        .Where(e => e.Name.LocalName == "path" && (string)e.Attribute(androidNs + "name") == path)
        .First();
    pathElement.SetAttributeValue(androidNs + "fillColor", colour);
}
