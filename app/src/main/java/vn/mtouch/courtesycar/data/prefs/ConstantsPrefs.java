package vn.mtouch.courtesycar.data.prefs;

import android.content.Context;
import android.graphics.BitmapFactory;

import vn.mtouch.courtesycar.CourtesyCarApp;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.utils.ImageUtils;

/**
 * Created by 14617 on 22/1/2018.
 */

public class ConstantsPrefs {

    public static final String KEY_SHARED = "com.evoteam.ecolauser.KEY_SHARED";

    public static final String IMPORT_PATH = "com.evoteam.ecolauser.IMPORT_PATH";
    public static final String STORE_CODE = "com.evoteam.ecolauser.STORE_CODE";


    public static final String FILTER_FROM_DATE_VALUE = "com.evoteam.ecolauser.FILTER_FROM_DATE";// long
    public static final String FILTER_BY_DATE_FLAG = "com.evoteam.ecolauser.FILTER_BY_DATE";// 0 1
    public static final String FILTER_TO_DATE_VALUE = "com.evoteam.ecolauser.FILTER_TO_DATE"; // long
    public static final String FILTER_BY_STATUS = "com.evoteam.ecolauser.FILTER_BY_STATUS";// int

    public static final String TERM = "com.evoteam.ecolauser.TERM";// int


    public static final String FOLDER_GOOGLE_DRIVE = "com.evoteam.ecolauser.FOLDER_GOOGLE_DRIVE";// int

    public static void saveTerm(Context context, String content) {
        SharePreferenceManager.putString(context, TERM, content);
    }

    public static String getStrHTML(Context context) {
        return SharePreferenceManager.getString(context, TERM, TERM_DEFAULT);
//        .replace("{{image001}}",
//                        ImageUtils.getEncoded64ImageStringFromBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.image001))
//                ).replace("{{image002}}",
//                        ImageUtils.getEncoded64ImageStringFromBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.image002))
//                );
    }

    public static String TERM_DEFAULT = "Terms & Conditions for the use of \"courtesy car\" \n" +
            "\n" +
            "￼PMA AUTO WORKS AUSTRALIA\n" +
            "\n" +
            "2/13 Molan St, Ringwood VIC 3134\n" +
            "\n" +
            "T: 03 8838 0594   | M: 0413 328 786\n" +
            "\n" +
            "E: info@pmaautoworks.com.au\n" +
            "\n" +
            "ABN 84 578 449 040\n" +
            "\n" +
            " \n" +
            "\n" +
            "TERMS & CONDITIONS FOR THE USE OF THE COURTESY CAR\n" +
            "\n" +
            "PMA AUTO WORKS is pleased to offer the use of a Courtesy Car, subject to the following terms and conditions: \n" +
            "\n" +
            "·       The Courtesy car will be insured COMPREHENSIVE PROTECTION by PMA Auto Works and maintained in a roadworthy and legal condition. \n" +
            "\n" +
            "The driver will:\n" +
            "\n" +
            "·       hold a Driving Licence.\n" +
            "\n" +
            "·       only use the Courtesy Car for Social, Domestic and Pleasure purposes.\n" +
            "\n" +
            "·       advise the proprietor of any speeding or parking offences which occur during the usage of the Courtesy Car.\n" +
            "\n" +
            "·       be responsible for payment of any penalties arising from speeding or parking offences incurred whilst in charge of the Courtesy Car.\n" +
            "\n" +
            "·       be responsible for payment of any trips travel on any Australia toll roads\n" +
            "\n" +
            "·       replace fuel used prior to returning to PMA Auto Works. \n" +
            "\n" +
            "·       return the Courtesy Car in good condition.\n" +
            "\n" +
            "·       advise the proprietor of any defects or any accidents that occur immediately thereafter by telephone. \n" +
            "\n" +
            "·       be solely responsible for payment of any repairs to vehicles involved in any accident deemed to be the fault of the driver of the Courtesy Car. \n" +
            "\n" +
            "·       be responsible for payment of insurance excess fees.\n" +
            "\n" +
            "Standard excess is $750 \n" +
            "\n" +
            "Driver Age excesses (in addition to the standard excess)\n" +
            "\n" +
            "Drivers aged under 17 years:                          $1,000\n" +
            "\n" +
            "Drivers aged under 19 years:                          $850    \n" +
            "\n" +
            "Drivers aged under 21 years:                          $650\n" +
            "\n" +
            "Drivers aged under 25 years:                          $400\n" +
            "\n" +
            "·       lock and secure the Courtesy car when not in use. \n" +
            "\n" +
            "The driver MUST not: \n" +
            "\n" +
            "·       exceed the speed limit or commit any driving offences.\n" +
            "\n" +
            "·       park in any restricted area which may result in a Parking Ticket or wheel clamping. \n" +
            "\n" +
            "·       smoke in the Courtesy Car or be under the influence of alcohol or drugs.\n" +
            "\n" +
            "PMA Auto Works reserves the right to withdraw the Courtesy Car without notice.\n" +
            "\n" +
            "I (the driver) hereby agree to the Terms and Conditions above:\n" +
            "\n";

//    public static String TERM_DEFAULT = "<html xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
//            "xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
//            "xmlns:w=\"urn:schemas-microsoft-com:office:word\"\n" +
//            "xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\"\n" +
//            "xmlns=\"http://www.w3.org/TR/REC-html40\">\n" +
//            "\n" +
//            "<head>\n" +
//            "<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n" +
//            "<meta name=ProgId content=Word.Document>\n" +
//            "<meta name=Generator content=\"Microsoft Word 15\">\n" +
//            "<meta name=Originator content=\"Microsoft Word 15\">\n" +
//            "<link rel=File-List\n" +
//            "href=\"Courtesy%20car%20terms%20and%20conditions.fld/filelist.xml\">\n" +
//            "<link rel=Edit-Time-Data\n" +
//            "href=\"Courtesy%20car%20terms%20and%20conditions.fld/editdata.mso\">\n" +
//            "<!--[if !mso]>\n" +
//            "<style>\n" +
//            "v\\:* {behavior:url(#default#VML);}\n" +
//            "o\\:* {behavior:url(#default#VML);}\n" +
//            "w\\:* {behavior:url(#default#VML);}\n" +
//            ".shape {behavior:url(#default#VML);}\n" +
//            "</style>\n" +
//            "<![endif]-->\n" +
//            "<title>Terms &amp; Conditions for the use of &quot;courtesy car&quot;</title>\n" +
//            "<!--[if gte mso 9]><xml>\n" +
//            " <o:DocumentProperties>\n" +
//            "  <o:Author>Martyn</o:Author>\n" +
//            "  <o:LastAuthor>Nguyen Truong Vu</o:LastAuthor>\n" +
//            "  <o:Revision>2</o:Revision>\n" +
//            "  <o:TotalTime>45</o:TotalTime>\n" +
//            "  <o:LastPrinted>2016-11-22T03:06:00Z</o:LastPrinted>\n" +
//            "  <o:Created>2018-12-04T08:59:00Z</o:Created>\n" +
//            "  <o:LastSaved>2018-12-04T08:59:00Z</o:LastSaved>\n" +
//            "  <o:Pages>1</o:Pages>\n" +
//            "  <o:Words>291</o:Words>\n" +
//            "  <o:Characters>1663</o:Characters>\n" +
//            "  <o:Company>Home</o:Company>\n" +
//            "  <o:Lines>13</o:Lines>\n" +
//            "  <o:Paragraphs>3</o:Paragraphs>\n" +
//            "  <o:CharactersWithSpaces>1951</o:CharactersWithSpaces>\n" +
//            "  <o:Version>16.00</o:Version>\n" +
//            " </o:DocumentProperties>\n" +
//            " <o:OfficeDocumentSettings>\n" +
//            "  <o:TargetScreenSize>800x600</o:TargetScreenSize>\n" +
//            " </o:OfficeDocumentSettings>\n" +
//            "</xml><![endif]-->\n" +
//            "<link rel=themeData\n" +
//            "href=\"Courtesy%20car%20terms%20and%20conditions.fld/themedata.thmx\">\n" +
//            "<link rel=colorSchemeMapping\n" +
//            "href=\"Courtesy%20car%20terms%20and%20conditions.fld/colorschememapping.xml\">\n" +
//            "<!--[if gte mso 9]><xml>\n" +
//            " <w:WordDocument>\n" +
//            "  <w:TrackMoves>false</w:TrackMoves>\n" +
//            "  <w:TrackFormatting/>\n" +
//            "  <w:ValidateAgainstSchemas/>\n" +
//            "  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>\n" +
//            "  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>\n" +
//            "  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>\n" +
//            "  <w:DoNotPromoteQF/>\n" +
//            "  <w:LidThemeOther>EN-US</w:LidThemeOther>\n" +
//            "  <w:LidThemeAsian>X-NONE</w:LidThemeAsian>\n" +
//            "  <w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript>\n" +
//            "  <w:Compatibility>\n" +
//            "   <w:BreakWrappedTables/>\n" +
//            "   <w:SnapToGridInCell/>\n" +
//            "   <w:WrapTextWithPunct/>\n" +
//            "   <w:UseAsianBreakRules/>\n" +
//            "   <w:UseWord2002TableStyleRules/>\n" +
//            "   <w:UseWord2010TableStyleRules/>\n" +
//            "   <w:DontUseIndentAsNumberingTabStop/>\n" +
//            "   <w:FELineBreak11/>\n" +
//            "   <w:WW11IndentRules/>\n" +
//            "   <w:DontAutofitConstrainedTables/>\n" +
//            "   <w:AutofitLikeWW11/>\n" +
//            "   <w:HangulWidthLikeWW11/>\n" +
//            "   <w:UseNormalStyleForList/>\n" +
//            "   <w:DontVertAlignCellWithSp/>\n" +
//            "   <w:DontBreakConstrainedForcedTables/>\n" +
//            "   <w:DontVertAlignInTxbx/>\n" +
//            "   <w:Word11KerningPairs/>\n" +
//            "   <w:CachedColBalance/>\n" +
//            "  </w:Compatibility>\n" +
//            "  <w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel>\n" +
//            "  <m:mathPr>\n" +
//            "   <m:mathFont m:val=\"Cambria Math\"/>\n" +
//            "   <m:brkBin m:val=\"before\"/>\n" +
//            "   <m:brkBinSub m:val=\"&#45;-\"/>\n" +
//            "   <m:smallFrac m:val=\"off\"/>\n" +
//            "   <m:dispDef/>\n" +
//            "   <m:lMargin m:val=\"0\"/>\n" +
//            "   <m:rMargin m:val=\"0\"/>\n" +
//            "   <m:defJc m:val=\"centerGroup\"/>\n" +
//            "   <m:wrapIndent m:val=\"1440\"/>\n" +
//            "   <m:intLim m:val=\"subSup\"/>\n" +
//            "   <m:naryLim m:val=\"undOvr\"/>\n" +
//            "  </m:mathPr></w:WordDocument>\n" +
//            "</xml><![endif]--><!--[if gte mso 9]><xml>\n" +
//            " <w:LatentStyles DefLockedState=\"false\" DefUnhideWhenUsed=\"false\"\n" +
//            "  DefSemiHidden=\"false\" DefQFormat=\"false\" LatentStyleCount=\"375\">\n" +
//            "  <w:LsdException Locked=\"false\" QFormat=\"true\" Name=\"Normal\"/>\n" +
//            "  <w:LsdException Locked=\"false\" QFormat=\"true\" Name=\"heading 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"heading 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"heading 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"heading 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"heading 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"heading 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"heading 7\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"heading 8\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"heading 9\"/>\n" +
//            "  <w:LsdException Locked=\"false\" SemiHidden=\"true\" UnhideWhenUsed=\"true\"\n" +
//            "   QFormat=\"true\" Name=\"caption\"/>\n" +
//            "  <w:LsdException Locked=\"false\" QFormat=\"true\" Name=\"Title\"/>\n" +
//            "  <w:LsdException Locked=\"false\" QFormat=\"true\" Name=\"Subtitle\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"99\" Name=\"Hyperlink\"/>\n" +
//            "  <w:LsdException Locked=\"false\" QFormat=\"true\" Name=\"Strong\"/>\n" +
//            "  <w:LsdException Locked=\"false\" QFormat=\"true\" Name=\"Emphasis\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"99\" SemiHidden=\"true\"\n" +
//            "   Name=\"Placeholder Text\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"1\" QFormat=\"true\" Name=\"No Spacing\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"60\" Name=\"Light Shading\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"61\" Name=\"Light List\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"62\" Name=\"Light Grid\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"63\" Name=\"Medium Shading 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"64\" Name=\"Medium Shading 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"65\" Name=\"Medium List 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"66\" Name=\"Medium List 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"67\" Name=\"Medium Grid 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"68\" Name=\"Medium Grid 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"69\" Name=\"Medium Grid 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"70\" Name=\"Dark List\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"71\" Name=\"Colorful Shading\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"72\" Name=\"Colorful List\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"73\" Name=\"Colorful Grid\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"60\" Name=\"Light Shading Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"61\" Name=\"Light List Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"62\" Name=\"Light Grid Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"63\" Name=\"Medium Shading 1 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"64\" Name=\"Medium Shading 2 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"65\" Name=\"Medium List 1 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"99\" SemiHidden=\"true\" Name=\"Revision\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"34\" QFormat=\"true\"\n" +
//            "   Name=\"List Paragraph\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"29\" QFormat=\"true\" Name=\"Quote\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"30\" QFormat=\"true\"\n" +
//            "   Name=\"Intense Quote\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"66\" Name=\"Medium List 2 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"67\" Name=\"Medium Grid 1 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"68\" Name=\"Medium Grid 2 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"69\" Name=\"Medium Grid 3 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"70\" Name=\"Dark List Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"71\" Name=\"Colorful Shading Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"72\" Name=\"Colorful List Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"73\" Name=\"Colorful Grid Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"60\" Name=\"Light Shading Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"61\" Name=\"Light List Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"62\" Name=\"Light Grid Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"63\" Name=\"Medium Shading 1 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"64\" Name=\"Medium Shading 2 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"65\" Name=\"Medium List 1 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"66\" Name=\"Medium List 2 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"67\" Name=\"Medium Grid 1 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"68\" Name=\"Medium Grid 2 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"69\" Name=\"Medium Grid 3 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"70\" Name=\"Dark List Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"71\" Name=\"Colorful Shading Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"72\" Name=\"Colorful List Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"73\" Name=\"Colorful Grid Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"60\" Name=\"Light Shading Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"61\" Name=\"Light List Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"62\" Name=\"Light Grid Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"63\" Name=\"Medium Shading 1 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"64\" Name=\"Medium Shading 2 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"65\" Name=\"Medium List 1 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"66\" Name=\"Medium List 2 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"67\" Name=\"Medium Grid 1 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"68\" Name=\"Medium Grid 2 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"69\" Name=\"Medium Grid 3 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"70\" Name=\"Dark List Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"71\" Name=\"Colorful Shading Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"72\" Name=\"Colorful List Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"73\" Name=\"Colorful Grid Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"60\" Name=\"Light Shading Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"61\" Name=\"Light List Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"62\" Name=\"Light Grid Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"63\" Name=\"Medium Shading 1 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"64\" Name=\"Medium Shading 2 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"65\" Name=\"Medium List 1 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"66\" Name=\"Medium List 2 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"67\" Name=\"Medium Grid 1 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"68\" Name=\"Medium Grid 2 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"69\" Name=\"Medium Grid 3 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"70\" Name=\"Dark List Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"71\" Name=\"Colorful Shading Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"72\" Name=\"Colorful List Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"73\" Name=\"Colorful Grid Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"60\" Name=\"Light Shading Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"61\" Name=\"Light List Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"62\" Name=\"Light Grid Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"63\" Name=\"Medium Shading 1 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"64\" Name=\"Medium Shading 2 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"65\" Name=\"Medium List 1 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"66\" Name=\"Medium List 2 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"67\" Name=\"Medium Grid 1 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"68\" Name=\"Medium Grid 2 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"69\" Name=\"Medium Grid 3 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"70\" Name=\"Dark List Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"71\" Name=\"Colorful Shading Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"72\" Name=\"Colorful List Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"73\" Name=\"Colorful Grid Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"60\" Name=\"Light Shading Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"61\" Name=\"Light List Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"62\" Name=\"Light Grid Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"63\" Name=\"Medium Shading 1 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"64\" Name=\"Medium Shading 2 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"65\" Name=\"Medium List 1 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"66\" Name=\"Medium List 2 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"67\" Name=\"Medium Grid 1 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"68\" Name=\"Medium Grid 2 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"69\" Name=\"Medium Grid 3 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"70\" Name=\"Dark List Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"71\" Name=\"Colorful Shading Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"72\" Name=\"Colorful List Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"73\" Name=\"Colorful Grid Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"19\" QFormat=\"true\"\n" +
//            "   Name=\"Subtle Emphasis\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"21\" QFormat=\"true\"\n" +
//            "   Name=\"Intense Emphasis\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"31\" QFormat=\"true\"\n" +
//            "   Name=\"Subtle Reference\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"32\" QFormat=\"true\"\n" +
//            "   Name=\"Intense Reference\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"33\" QFormat=\"true\" Name=\"Book Title\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"37\" SemiHidden=\"true\"\n" +
//            "   UnhideWhenUsed=\"true\" Name=\"Bibliography\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"39\" SemiHidden=\"true\"\n" +
//            "   UnhideWhenUsed=\"true\" QFormat=\"true\" Name=\"TOC Heading\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"41\" Name=\"Plain Table 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"42\" Name=\"Plain Table 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"43\" Name=\"Plain Table 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"44\" Name=\"Plain Table 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"45\" Name=\"Plain Table 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"40\" Name=\"Grid Table Light\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\" Name=\"Grid Table 1 Light\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"Grid Table 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"Grid Table 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"Grid Table 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"Grid Table 5 Dark\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\" Name=\"Grid Table 6 Colorful\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\" Name=\"Grid Table 7 Colorful\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"Grid Table 1 Light Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"Grid Table 2 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"Grid Table 3 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"Grid Table 4 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"Grid Table 5 Dark Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"Grid Table 6 Colorful Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"Grid Table 7 Colorful Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"Grid Table 1 Light Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"Grid Table 2 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"Grid Table 3 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"Grid Table 4 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"Grid Table 5 Dark Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"Grid Table 6 Colorful Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"Grid Table 7 Colorful Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"Grid Table 1 Light Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"Grid Table 2 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"Grid Table 3 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"Grid Table 4 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"Grid Table 5 Dark Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"Grid Table 6 Colorful Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"Grid Table 7 Colorful Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"Grid Table 1 Light Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"Grid Table 2 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"Grid Table 3 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"Grid Table 4 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"Grid Table 5 Dark Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"Grid Table 6 Colorful Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"Grid Table 7 Colorful Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"Grid Table 1 Light Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"Grid Table 2 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"Grid Table 3 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"Grid Table 4 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"Grid Table 5 Dark Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"Grid Table 6 Colorful Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"Grid Table 7 Colorful Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"Grid Table 1 Light Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"Grid Table 2 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"Grid Table 3 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"Grid Table 4 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"Grid Table 5 Dark Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"Grid Table 6 Colorful Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"Grid Table 7 Colorful Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\" Name=\"List Table 1 Light\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"List Table 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"List Table 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"List Table 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"List Table 5 Dark\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\" Name=\"List Table 6 Colorful\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\" Name=\"List Table 7 Colorful\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"List Table 1 Light Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"List Table 2 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"List Table 3 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"List Table 4 Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"List Table 5 Dark Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"List Table 6 Colorful Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"List Table 7 Colorful Accent 1\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"List Table 1 Light Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"List Table 2 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"List Table 3 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"List Table 4 Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"List Table 5 Dark Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"List Table 6 Colorful Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"List Table 7 Colorful Accent 2\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"List Table 1 Light Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"List Table 2 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"List Table 3 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"List Table 4 Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"List Table 5 Dark Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"List Table 6 Colorful Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"List Table 7 Colorful Accent 3\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"List Table 1 Light Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"List Table 2 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"List Table 3 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"List Table 4 Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"List Table 5 Dark Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"List Table 6 Colorful Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"List Table 7 Colorful Accent 4\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"List Table 1 Light Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"List Table 2 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"List Table 3 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"List Table 4 Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"List Table 5 Dark Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"List Table 6 Colorful Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"List Table 7 Colorful Accent 5\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"46\"\n" +
//            "   Name=\"List Table 1 Light Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"47\" Name=\"List Table 2 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"48\" Name=\"List Table 3 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"49\" Name=\"List Table 4 Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"50\" Name=\"List Table 5 Dark Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"51\"\n" +
//            "   Name=\"List Table 6 Colorful Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"52\"\n" +
//            "   Name=\"List Table 7 Colorful Accent 6\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"99\" SemiHidden=\"true\"\n" +
//            "   UnhideWhenUsed=\"true\" Name=\"Mention\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"99\" SemiHidden=\"true\"\n" +
//            "   UnhideWhenUsed=\"true\" Name=\"Smart Hyperlink\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"99\" SemiHidden=\"true\"\n" +
//            "   UnhideWhenUsed=\"true\" Name=\"Hashtag\"/>\n" +
//            "  <w:LsdException Locked=\"false\" Priority=\"99\" SemiHidden=\"true\"\n" +
//            "   UnhideWhenUsed=\"true\" Name=\"Unresolved Mention\"/>\n" +
//            " </w:LatentStyles>\n" +
//            "</xml><![endif]-->\n" +
//            "<style>\n" +
//            "</style>\n" +
//            "<!--[if gte mso 10]>\n" +
//            "<style>\n" +
//            " /* Style Definitions */\n" +
//            " table.MsoNormalTable\n" +
//            "\t{mso-style-name:\"Table Normal\";\n" +
//            "\tmso-tstyle-rowband-size:0;\n" +
//            "\tmso-tstyle-colband-size:0;\n" +
//            "\tmso-style-noshow:yes;\n" +
//            "\tmso-style-unhide:no;\n" +
//            "\tmso-style-parent:\"\";\n" +
//            "\tmso-padding-alt:0cm 5.4pt 0cm 5.4pt;\n" +
//            "\tmso-para-margin:0cm;\n" +
//            "\tmso-para-margin-bottom:.0001pt;\n" +
//            "\tmso-pagination:widow-orphan;\n" +
//            "\tfont-size:10.0pt;\n" +
//            "\tfont-family:\"Times New Roman\",serif;}\n" +
//            "</style>\n" +
//            "<![endif]--><!--[if gte mso 9]><xml>\n" +
//            " <o:shapedefaults v:ext=\"edit\" spidmax=\"1027\"/>\n" +
//            "</xml><![endif]--><!--[if gte mso 9]><xml>\n" +
//            " <o:shapelayout v:ext=\"edit\">\n" +
//            "  <o:idmap v:ext=\"edit\" data=\"1\"/>\n" +
//            " </o:shapelayout></xml><![endif]-->\n" +
//            "</head>\n" +
//            "\n" +
//            "<body lang=EN-US link=\"#0563C1\" vlink=\"#954F72\" style='tab-interval:36.0pt'>\n" +
//            "\n" +
//            "<div class=WordSection1>\n" +
//            "\n" +
//            "<p class=MsoNormal align=right style='text-align:right'><!--[if gte vml 1]><v:shapetype\n" +
//            " id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\"\n" +
//            " path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\">\n" +
//            " <v:stroke joinstyle=\"miter\"/>\n" +
//            " <v:formulas>\n" +
//            "  <v:f eqn=\"if lineDrawn pixelLineWidth 0\"/>\n" +
//            "  <v:f eqn=\"sum @0 1 0\"/>\n" +
//            "  <v:f eqn=\"sum 0 0 @1\"/>\n" +
//            "  <v:f eqn=\"prod @2 1 2\"/>\n" +
//            "  <v:f eqn=\"prod @3 21600 pixelWidth\"/>\n" +
//            "  <v:f eqn=\"prod @3 21600 pixelHeight\"/>\n" +
//            "  <v:f eqn=\"sum @0 0 1\"/>\n" +
//            "  <v:f eqn=\"prod @6 1 2\"/>\n" +
//            "  <v:f eqn=\"prod @7 21600 pixelWidth\"/>\n" +
//            "  <v:f eqn=\"sum @8 21600 0\"/>\n" +
//            "  <v:f eqn=\"prod @7 21600 pixelHeight\"/>\n" +
//            "  <v:f eqn=\"sum @10 21600 0\"/>\n" +
//            " </v:formulas>\n" +
//            " <v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/>\n" +
//            " <o:lock v:ext=\"edit\" aspectratio=\"t\"/>\n" +
//            "</v:shapetype><v:shape id=\"_x0000_s1026\" type=\"#_x0000_t75\" alt=\"Logo_PMA\"\n" +
//            " style='position:absolute;left:0;text-align:left;margin-left:24pt;margin-top:-.65pt;\n" +
//            " width:199.5pt;height:87.85pt;z-index:251657728;mso-wrap-edited:f;\n" +
//            " mso-width-percent:0;mso-height-percent:0;mso-width-percent:0;\n" +
//            " mso-height-percent:0'>\n" +
//            " <v:imagedata src=\"data:image/png;base64,{{image001}}\"\n" +
//            "  o:title=\"Logo_PMA\"/>\n" +
//            " <w:wrap type=\"square\"/>\n" +
//            "</v:shape><![endif]--><![if !vml]><img width=200 height=88\n" +
//            "src=\"data:image/png;base64,{{image002}}\" align=left\n" +
//            "hspace=12 alt=\"Logo_PMA\" v:shapes=\"_x0000_s1026\"><![endif]><b style='mso-bidi-font-weight:\n" +
//            "normal'><span style='font-family:\"Arial\",sans-serif'>PMA AUTO WORKS AUSTRALIA<o:p></o:p></span></b></p>\n" +
//            "\n" +
//            "<p class=MsoNormal align=right style='text-align:right;line-height:115%'><span\n" +
//            "style='font-family:\"Arial\",sans-serif'>2/13 Molan St, Ringwood VIC 3134<o:p></o:p></span></p>\n" +
//            "\n" +
//            "<p class=MsoNormal align=right style='text-align:right;line-height:115%'><span\n" +
//            "style='font-family:\"Arial\",sans-serif'>T: 03 8838 0594<span\n" +
//            "style='mso-spacerun:yes'>   </span>| M: 0413 328 786<o:p></o:p></span></p>\n" +
//            "\n" +
//            "<p class=MsoNormal align=right style='text-align:right;line-height:115%'><span\n" +
//            "style='font-family:\"Arial\",sans-serif'>E: info@pmaautoworks.com.au<o:p></o:p></span></p>\n" +
//            "\n" +
//            "<p class=MsoNormal align=right style='text-align:right;line-height:115%'><span\n" +
//            "style='font-family:\"Arial\",sans-serif'>ABN 84 578 449 040<o:p></o:p></span></p>\n" +
//            "\n" +
//            "<p class=MsoNormal align=right style='text-align:right;line-height:115%'><span\n" +
//            "class=t1><o:p>&nbsp;</o:p></span></p>\n" +
//            "\n" +
//            "<p class=p1 align=center style='text-align:center'><span class=t1><span\n" +
//            "style='font-size:14.0pt;font-family:\"Arial Black\",sans-serif'>TERMS &amp;\n" +
//            "CONDITIONS FOR THE USE OF THE COURTESY CAR<o:p></o:p></span></span></p>\n" +
//            "\n" +
//            "<p class=p1><span class=t1><b style='mso-bidi-font-weight:normal'><span\n" +
//            "style='font-size:10.0pt;font-family:\"Arial\",sans-serif'>PMA AUTO WORKS is\n" +
//            "pleased to offer the use of a Courtesy Car, subject to the following terms and\n" +
//            "conditions: <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>The Courtesy car will be insured COMPREHENSIVE\n" +
//            "PROTECTION by PMA Auto Works and maintained in a roadworthy and legal\n" +
//            "condition. <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:18.0pt'><span class=t1><b style='mso-bidi-font-weight:\n" +
//            "normal'><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif'>The\n" +
//            "driver will:<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>hold a Driving Licence.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>only use the Courtesy Car for Social, Domestic\n" +
//            "and Pleasure purposes.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>advise the proprietor of any speeding or\n" +
//            "parking offences which occur during the usage of the Courtesy Car.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>be responsible for payment of any penalties\n" +
//            "arising from speeding or parking offences incurred whilst in charge of the\n" +
//            "Courtesy Car.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>be responsible for payment of any trips travel\n" +
//            "on any Australia toll roads<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>replace fuel used prior to returning to PMA\n" +
//            "Auto Works. <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>return the Courtesy Car in good condition.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>advise the proprietor of any defects or any\n" +
//            "accidents that occur immediately thereafter by telephone. <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>be solely responsible for payment of any\n" +
//            "repairs to vehicles involved in any accident deemed to be the fault of the\n" +
//            "driver of the Courtesy Car. <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-top:5.0pt;margin-right:0cm;margin-bottom:0cm;\n" +
//            "margin-left:36.0pt;margin-bottom:.0001pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>be responsible for payment of insurance excess\n" +
//            "fees.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;\n" +
//            "margin-left:36.0pt;margin-bottom:.0001pt'><span class=t1><b style='mso-bidi-font-weight:\n" +
//            "normal'><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif'>Standard\n" +
//            "excess is $750 <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;\n" +
//            "margin-left:36.0pt;margin-bottom:.0001pt'><span class=t1><b style='mso-bidi-font-weight:\n" +
//            "normal'><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif'>Driver\n" +
//            "Age excesses (in addition to the standard excess)<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;\n" +
//            "margin-left:72.0pt;margin-bottom:.0001pt;tab-stops:288.0pt'><span class=t1><b\n" +
//            "style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;font-family:\n" +
//            "\"Arial\",sans-serif'>Drivers aged under 17 years:<span style='mso-tab-count:\n" +
//            "1'>                          </span>$1,000<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;\n" +
//            "margin-left:72.0pt;margin-bottom:.0001pt;tab-stops:288.0pt'><span class=t1><b\n" +
//            "style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;font-family:\n" +
//            "\"Arial\",sans-serif'>Drivers aged under 19 years:<span style='mso-tab-count:\n" +
//            "1'>                          </span>$850<span style='mso-tab-count:1'>    </span><o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;\n" +
//            "margin-left:72.0pt;margin-bottom:.0001pt;tab-stops:288.0pt'><span class=t1><b\n" +
//            "style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;font-family:\n" +
//            "\"Arial\",sans-serif'>Drivers aged under 21 years:<span style='mso-tab-count:\n" +
//            "1'>                          </span>$650<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;\n" +
//            "margin-left:72.0pt;margin-bottom:.0001pt;tab-stops:288.0pt'><span class=t1><b\n" +
//            "style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;font-family:\n" +
//            "\"Arial\",sans-serif'>Drivers aged under 25 years:<span style='mso-tab-count:\n" +
//            "1'>                          </span>$400<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-top:0cm;margin-right:0cm;margin-bottom:5.0pt;\n" +
//            "margin-left:36.0pt;text-indent:-18.0pt;mso-list:l0 level1 lfo1;tab-stops:list 36.0pt'><![if !supportLists]><span\n" +
//            "class=t1><span style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:\n" +
//            "Symbol;mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>lock and secure the Courtesy car when not in\n" +
//            "use. <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:18.0pt'><span class=t1><b style='mso-bidi-font-weight:\n" +
//            "normal'><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif'>The\n" +
//            "driver MUST not: <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l1 level1 lfo3;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>exceed the speed limit or commit any driving\n" +
//            "offences.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l1 level1 lfo3;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>park in any restricted area which may result in\n" +
//            "a Parking Ticket or wheel clamping. <o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:36.0pt;text-indent:-18.0pt;mso-list:l1 level1 lfo3;\n" +
//            "tab-stops:list 36.0pt'><![if !supportLists]><span class=t1><span\n" +
//            "style='font-size:10.0pt;font-family:Symbol;mso-fareast-font-family:Symbol;\n" +
//            "mso-bidi-font-family:Symbol'><span style='mso-list:Ignore'>·<span\n" +
//            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></span><![endif]><span\n" +
//            "class=t1><b style='mso-bidi-font-weight:normal'><span style='font-size:10.0pt;\n" +
//            "font-family:\"Arial\",sans-serif'>smoke in the Courtesy Car or be under the\n" +
//            "influence of alcohol or drugs.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:18.0pt'><span class=t1><b style='mso-bidi-font-weight:\n" +
//            "normal'><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif'>PMA Auto\n" +
//            "Works reserves the right to withdraw the Courtesy Car without notice.<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "<p class=p1 style='margin-left:18.0pt'><span class=t1><b style='mso-bidi-font-weight:\n" +
//            "normal'><span style='font-size:10.0pt;font-family:\"Arial Black\",sans-serif;\n" +
//            "mso-bidi-font-family:Arial'>I (the driver) hereby agree to the Terms and\n" +
//            "Conditions above:<o:p></o:p></span></b></span></p>\n" +
//            "\n" +
//            "</div>\n" +
//            "\n" +
//            "</body>\n" +
//            "\n" +
//            "</html>\n";
}