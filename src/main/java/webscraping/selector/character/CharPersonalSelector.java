package webscraping.selector.character;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import webscraping.model.character.CharPersonal;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CharPersonalSelector {
    private CharPersonalSelector() {
    }

    public static CharPersonal getInfoPersonal(Document doc) {
        CharPersonal charPersonal = new CharPersonal();

        //birthdate
        Elements birthdateElements = doc.select("th:containsOwn(Birthdate)");
        if (!birthdateElements.isEmpty()) {
            String birthdate = birthdateElements.first().parent().children().get(1).text();
            charPersonal.setBirthDate(birthdate);
        }

        //sex
        Elements sexElements = doc.select("th:containsOwn(Sex)");
        if (!sexElements.isEmpty()) {
            String sex = sexElements.first().parent().children().get(1).text();
            charPersonal.setSex(sex);
        }

        //age
        List<String> ages = new ArrayList<>();
        Elements ageElements = doc.select("th:containsOwn(Age)");
        if (!ageElements.isEmpty()) {
            for (Element element : ageElements.first().parent().getElementsByTag("li")) {
                ages.add(element.text().trim());
            }
            charPersonal.setAge(ages);
        }

        //specie
        Elements specieElements = doc.select("th:containsOwn(Species)");
        if (!specieElements.isEmpty()) {
            String specie = specieElements.first().parent().children().select("td").text().trim();
            charPersonal.setSpecie(specie);
        }

        //status
        Elements statusElements = doc.select("[title=\"Property:Status\"]");
        if (!statusElements.isEmpty()) {
            String status = statusElements.parents().get(1).select(".smwprops").first().text();
            charPersonal.setStatus(status.substring(0, status.lastIndexOf("+")).trim());
        }

        //height
        List<String> heights = new ArrayList<>();
        Elements heightElements = doc.select("th:containsOwn(Height)");
        if (!heightElements.isEmpty()) {
            for (String height : heightElements.first().parent().children().select("ul li").eachText()) {
                if (height.contains("Part") || height.contains("Blank") || height.contains("Boruto")) {
                    String heightSubs = height.substring(0, height.lastIndexOf("cm") + 2);
                    if (heightSubs.contains(" m")) {
                        String part1 = heightSubs.substring(0, heightSubs.indexOf("cm") + 2);
                        String part2 = heightSubs.substring(heightSubs.indexOf(">–") + 1);
                        heights.add(part1 + part2);
                    } else {
                        heights.add(heightSubs);
                    }
                }
            }
            charPersonal.setHeight(heights);
        }

        //weight
        List<String> weights = new ArrayList<>();
        Elements weightElements = doc.select("th:containsOwn(Weight)");
        if (!weightElements.isEmpty()) {
            for (String weight : weightElements.first().parent().children().select("ul li").eachText()) {
                if (weight.contains("Part") || weight.contains("Blank") || weight.contains("Boruto")) {
                    String weightSubs = weight.substring(0, weight.lastIndexOf("kg") + 2);
                    if (weightSubs.contains(" lb")) {
                        String part1 = weightSubs.substring(0, weightSubs.indexOf("kg") + 2);
                        String part2 = weightSubs.substring(weightSubs.indexOf(">–") + 1);
                        weights.add(part1 + part2);
                    } else {
                        weights.add(weightSubs);
                    }
                }
            }
            charPersonal.setWeight(weights);
        }

        //bloodtype
        Elements bloodTypeElements = doc.select("th:containsOwn(Blood type)");
        if (!bloodTypeElements.isEmpty()) {
            String bloodType = bloodTypeElements.first().parent().children().get(1).text();
            charPersonal.setBloodType(bloodType);
        }

        //kekkei mora
        List<String> kekkeiMora = new ArrayList<>();
        Elements kekkeiMoraElmts = doc.select("th:containsOwn(Kekkei Mōra)");
        if (!kekkeiMoraElmts.isEmpty()) {
            for (Element element : kekkeiMoraElmts.first().parent().children().select("td ul li a:not(:has(img))")) {
                kekkeiMora.add(element.text().trim());
            }
            charPersonal.setKekkeiMora(kekkeiMora);
        }

        //kekkei genkai
        List<String> kekkeiGenkais = new ArrayList<>();
        Elements kekkeiGenkaiElements = doc.select("th:containsOwn(Kekkei Genkai)");
        if (!kekkeiGenkaiElements.isEmpty()) {
            for (Element element : kekkeiGenkaiElements.first().parent().children().select("td ul li a:not(:has(img))")) {
                kekkeiGenkais.add(element.text().trim());
            }
            charPersonal.setKekkeiGenkai(kekkeiGenkais);
        }

        //classification
        List<String> classifications = new ArrayList<>();
        Elements classificationElements = doc.select("th:containsOwn(Classification)");
        if (!classificationElements.isEmpty()) {
            for (Element element :
                    classificationElements.first().parent().children().select("td ul li a")) {
                classifications.add(element.text().trim());
            }
            charPersonal.setClassification(classifications);
        }

        //tailed beast
        List<String> tailedBeasts = new ArrayList<>();
        Elements tailedBeastElements = doc.select("th:containsOwn(Tailed Beast)");
        if (!tailedBeastElements.isEmpty()) {
            for (Element element : tailedBeastElements.first().parent().children().select("td a")) {
                tailedBeasts.add(element.text().trim());
            }
            charPersonal.setTailedBeast(tailedBeasts);
        }

        //occupation
        List<String> occupations = new ArrayList<>();
        Elements occupationElements = doc.select("th:containsOwn(Occupation)");
        if (!occupationElements.isEmpty()) {
            for (Element element : occupationElements.first().parent().children().select("td ul li")) {
                String occupation = element.text();
                if (occupation.contains(" (")) {
                    occupations.add(occupation.substring(0, occupation.indexOf(" (")).trim());
                } else {
                    occupations.add(element.text().trim());
                }
            }
            charPersonal.setOccupation(occupations);
        }

        //affiliation
        List<String> affiliations = new ArrayList<>();
        Elements affiliationElements = doc.select("th:containsOwn(Affiliation)");
        if (!doc.select("th:contains(Affiliation)").isEmpty()) {
            for (Element element : affiliationElements.first().parent().children().select("ul li a:not(:has(img))")) {
                String affiliation = element.text();
                if (affiliation.contains(" (")) {
                    affiliations.add(affiliation.substring(0, affiliation.indexOf(" (")).trim());
                } else {
                    affiliations.add(element.text().trim());
                }
            }
            charPersonal.setAffiliation(affiliations);
        }

        //team
        List<String> teams = new ArrayList<>();
        Elements teamElements = doc.select("th:containsOwn(Team)");
        if (!teamElements.isEmpty()) {
            for (Element element : teamElements.first().parent().children().select("ul li a:not(:has(img))")) {
                teams.add(element.text().trim());
            }
            charPersonal.setTeam(teams);
        }

        //partner
        List<String> partners = new ArrayList<>();
        Elements partnerElements = doc.select("th:containsOwn(Partner)");
        if (!partnerElements.isEmpty()) {
            for (Element element : partnerElements.first().parent().children().select("td ul li a")) {
                partners.add(element.text().trim());
            }
            charPersonal.setPartner(partners);
        }

        //clan
        List<String> clans = new ArrayList<>();
        Elements clanElements = doc.select("th:containsOwn(Clan)");
        if (!clanElements.isEmpty()) {
            for (Element element : clanElements.first().parent().children().select("td ul li")) {
                clans.add(element.text().trim());
            }
            charPersonal.setClan(clans);
        }
        log.info("Pesonal info getted.");
        return charPersonal;
    }
}
