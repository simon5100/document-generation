package com.simon.documentgeneration.util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Chunks {

    private Chunk[] chunkFretboard;

    private Chunk chunkFootnote;

    private Chunk chunkTitle;

    private Chunk[] chunkSection;

    private List<Chunk> chunkParagraph;

    private static int countFootnote = 1;

    private static Chunk lineFootnote = new Chunk("________________________");


    public Chunk[] getChunkFretboard(Font normalFont, Font fontInterlinear) {
        chunkFretboard = new Chunk[3];
        chunkFretboard[0] = new Chunk(
                "УТВЕРЖДАЮ\n" +
                        "Начальник управления по обеспечению\n" +
                        "деятельности мировых судей\n" +
                        "Новосибирской области\n" +
                        "\n" +
                        "          ______________  А.В. Артеменко\n", normalFont);
        chunkFretboard[1] = new Chunk("(подпись)", fontInterlinear);
        chunkFretboard[2] = new Chunk("«      »            202      г.", normalFont);

        return chunkFretboard;
    }

    public Chunk getChunkTitle(Font boldFont) {
        return chunkTitle = new Chunk(
                "ДОЛЖНОСТНОЙ РЕГЛАМЕНТ\n" +
                        "СЕКРЕТАРЯ СУДЕБНОГО ЗАСЕДАНИЯ АППАРАТА МИРОВЫХ СУДЕЙ\n" +
                        "ЖЕЛЕЗНОДОРОЖНОГО СУДЕБНОГО РАЙОНА УПРАВЛЕНИЯ ПО\n" +
                        "ОБЕСПЕЧЕНИЮ ДЕЯТЕЛЬНОСТИ МИРОВЫХ СУДЕЙ\n" +
                        "НОВОСИБИРСКОЙ ОБЛАСТИ",
                boldFont
        );
    }

    public Chunk[] getChunkSection(Font boldFont) {
        chunkSection = new Chunk[9];
        chunkSection[0] = new Chunk("I. Общие положения", boldFont);
        chunkSection[1] = new Chunk("II. Квалификационные требования для замещения должности " +
                "гражданской службы", boldFont);
        chunkSection[2] = new Chunk("III. Должностные обязанности", boldFont);
        chunkSection[3] = new Chunk("IV. Перечень вопросов, по которым гражданский служащий вправе или обязан самостоятельно принимать " +
                "управленческие и иные решения", boldFont);
        chunkSection[4] = new Chunk("V. Перечень вопросов, по которым гражданский служащий вправе или обязан участвовать в подготовке " +
                "проектов нормативных правовых актов и (или) проектов управленческих и иных решений", boldFont);
        chunkSection[5] = new Chunk("VI. Сроки и процедуры подготовки, рассмотрения проектов управленческих и иных решений, порядок " +
                "согласования и принятия данных решений", boldFont);
        chunkSection[6] = new Chunk("VII. Порядок служебного взаимодействия гражданского служащего в связи " +
                "с исполнением им должностных обязанностей с гражданскими служащими того же государственного органа, " +
                "гражданскими служащими иных государственных органов, другими гражданами, а также организациями", boldFont);
        chunkSection[7] = new Chunk("VIII. Перечень государственных услуг (видов деятельности), " +
                "оказываемых по запросам граждан и организаций в соответствии " +
                "с административным регламентом (иным нормативным правовым актом) государственного органа", boldFont);
        chunkSection[8] = new Chunk("IX. Показатели эффективности и результативности профессиональной служебной деятельности", boldFont);

        return chunkSection;
    }

    public List<Chunk> getChunksParagraph(int i, Font normalFont, Font boldFont, Font smallFont, Font fontFootnote) {
        chunkParagraph = new ArrayList<>();

        switch (i) {
            case 0:
                chunkParagraph.add(new Chunk("1. Должность государственной гражданской службы Новосибирской области " +
                        "(далее – гражданская служба): ", normalFont));
                chunkParagraph.add(new Chunk("секретарь судебного заседания аппарата " +
                        "мировых судей Железнодорожного судебного района ", boldFont));
                chunkParagraph.add(new Chunk("(далее – секретарь " +
                        "судебного заседания) относится к старшей группе должностей гражданской " +
                        "службы категории «специалисты».\n", normalFont));
                chunkParagraph.add(new Chunk("Регистрационный номер (код) должности", normalFont));
                chunkParagraph.add(new Chunk(String.valueOf(countFootnote++), smallFont).
                        setTextRise(5));
                chunkParagraph.add(new Chunk(" – 03-3-4-002-54.\n", normalFont));
                chunkParagraph.add(new Chunk("2. Область профессиональной служебной деятельности государственного " +
                        "гражданского служащего Новосибирской области", normalFont));
                chunkParagraph.add(new Chunk(String.valueOf(countFootnote++), smallFont).
                        setTextRise(5));
                chunkParagraph.add(new Chunk(" (далее – гражданский служащий): организация судопроизводства.\n", normalFont));
                chunkParagraph.add(new Chunk("3. Вид профессиональной служебной деятельности гражданского служащего", normalFont));
                chunkParagraph.add(new Chunk(String.valueOf(countFootnote++), smallFont).
                        setTextRise(5));
                chunkParagraph.add(new Chunk(": организационно – правовое обеспечение деятельности судов.\n", normalFont));
                chunkParagraph.add(new Chunk("4. Назначение на должность и освобождение от должности секретаря " +
                        "судебного заседания аппарата мировых судей Железнодорожного судебного района осуществляются " +
                        "начальником управления по обеспечению деятельности мировых судей Новосибирской области с учетом " +
                        "требований Закона Новосибирской области от 26.09.2005 № 314-ОЗ «О мировых судьях Новосибирской области».\n"
                        , normalFont));
                chunkParagraph.add(new Chunk("5. Секретарь судебного заседания непосредственно подчиняется", normalFont));
                chunkParagraph.add(new Chunk(" мировому судье 4-го судебного участка Железнодорожного судебного района", boldFont));
                chunkParagraph.add(new Chunk(" (далее – мировой судья).\n", normalFont));
                lineFootnote.setFont(normalFont);
                chunkParagraph.add(lineFootnote);
                chunkParagraph.add(new Chunk("1", fontFootnote).
                        setTextRise(5));
                chunkParagraph.add(new Chunk("В соответствии с Реестром должностей государственной гражданской службы Новосибирской области " +
                        "(приложение к Закону Новосибирской области от 06.04.2005 № 287-ОЗ «О Реестре должностей государственной " +
                        "гражданской службы Новосибирской области»).\n", smallFont));
                chunkParagraph.add(new Chunk("2", fontFootnote).
                        setTextRise(5));
                chunkParagraph.add(new Chunk(" В соответствии со справочником квалификационных требований к специальностям, направлениям подготовки (к " +
                        "укрупненным группам специальностей и направлений подготовки), к профессиональному уровню, которые " +
                        "необходимы для замещения должностей государственной гражданской службы, с учетом области и вида " +
                        "профессиональной служебной деятельности государственных гражданских служащих, предусмотренным частью 8 " +
                        "статьи 12 Федерального закона от 27.07.2004 № 79-ФЗ «О государственной гражданской службе Российской " +
                        "Федерации» (далее – Справочник).\n", smallFont));
                chunkParagraph.add(new Chunk("3", fontFootnote).
                        setTextRise(5));
                chunkParagraph.add(new Chunk(" В соответствии со Справочником.\n", smallFont));
                chunkParagraph.add(new Chunk("6. Секретарь судебного заседания обязан исполнять должностные обязанности:\n", normalFont));
                chunkParagraph.add(new Chunk("секретаря судебного участка аппарата мировых судей Железнодорожного судебного " +
                        "района г. Новосибирска, осуществляющего свою профессиональную деятельность на  4-м судебном участке " +
                        "Железнодорожного судебного района г. Новосибирска, в период его временного отсутствия.\n", normalFont));










                break;
            case 1:
                chunkParagraph.add(new Chunk(i + "\n", normalFont));
                break;
            case 2:

                chunkParagraph.add(new Chunk(i + "\n", normalFont));
                break;
            case 3:
                chunkParagraph.add(new Chunk(i + "\n", normalFont));
                break;
            case 4:
                chunkParagraph.add(new Chunk(i + "\n", normalFont));
                break;
            case 5:
                chunkParagraph.add(new Chunk(i + "\n", normalFont));
                break;
            case 6:
                chunkParagraph.add(new Chunk(i + "\n", normalFont));
                break;
            case 7:
                chunkParagraph.add(new Chunk(i + "\n", normalFont));
                break;
            case 8:
                chunkParagraph.add(new Chunk(i + "\n", normalFont));
                break;

                default:
                    break;
        }
        return chunkParagraph;
    }
}
