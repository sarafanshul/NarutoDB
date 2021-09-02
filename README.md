# Naruto Jsoup Springboot API

## Description
Web scraping API created using [JSOUP](https://jsoup.org) to get the Naruto anime data from
the [NarutoFandom](https://naruto.fandom.com/wiki/Narutopedia) web page and save it in the mongoDB database.

## Technologies used
* Java 
* Spring Boot
* JSOUP
* MongoDB

## Requests
```http
POST /character/{name}
POST /clan/{name}
POST /country/{name}
POST /jutsu/{name}
POST /kekkeigenkai/{name}
POST /team/{name}
POST /tool/{name}
POST /village/{name}
```

```http
GET /character/{name}
GET /clan/{name}
GET /country/{name}
GET /jutsu/{name}
GET /kekkeigenkai/{name}
GET /team/{name}
GET /tool/{name}
GET /village/{name}
```

### Example
* Use the same name found at the end of the url
    * https://naruto.fandom.com/wiki/Naruto_Uzumaki
* Use an HTTP POST request to save data in mongoDB
    * **localhost:8080/character/Naruto_Uzumaki**

### Character document example in mongoDB
```json
{
  "name": {
    "english": "Naruto Uzumaki",
    "kanji": "うずまきナルト",
    "romaji": "Uzumaki Naruto",
    "others": [
      "The Show-Off, Number One Unpredictable, Noisy Ninja (目立ちたがり屋で意外性No.のドタバタ忍者, Medachitagariya de Igaisei Nanbā Wan no Dotabata Ninja, English TV: Number One Hyperactive, Knucklehead Ninja)",
      "Child of the Prophecy (予言の子, Yogen no Ko)",
      "Saviour of this World (この世の救世主, Kono Yo no Kyūseishu)",
      "Hero of the Hidden Leaf (木ノ葉隠れの英雄, Konohagakure no Eiyū, literally meaning: Hero of the Hidden Tree Leaves)",
      "Boy of Miracles (奇跡を起こす少年, Kiseki o Okosu Shōnen)",
      "Konoha's Orange Hokage (木ノ葉のオレンジ火影, Konoha no Orenji Hokage, literally meaning: Tree Leaves' Orange Fire Shadow)",
      "Seventh Hokage (七代目火影, Nanadaime Hokage, literally meaning: Seventh Fire Shadow)",
      "Fox (狐, Kitsune)"
    ]
  },
  "description": "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage. In the following years, through many hardships and ordeals, he became a capable ninja regarded as a hero both by the villagers, and soon after, the rest of the world, becoming known as the Hero of the Hidden Leaf (木ノ葉隠れの英雄, Konohagakure no Eiyū, literally meaning: Hero of the Hidden Tree Leaves). He soon proved to be one of the main factors in winning the Fourth Shinobi World War, leading him to achieve his dream and become the village's Seventh Hokage (七代目火影, Nanadaime Hokage, literally meaning: Seventh Fire Shadow).",
  "images": [
    "base64 img string"
  ],
  "debut": {
    "manga": {
      "name": "Naruto",
      "volume": 1,
      "chapter": 1
    },
    "anime": {
      "name": "Naruto",
      "episode": 1
    },
    "novel": "Naruto: Innocent Heart, Demonic Blood",
    "movie": "Naruto the Movie: Ninja Clash in the Land of Snow",
    "game": "Naruto: Konoha Ninpōchō",
    "ova": "Find the Four-Leaf Red Clover!",
    "appears": [
      "Anime",
      "Manga",
      "Novel",
      "Game",
      "Movie"
    ]
  },
  "voices": {
    "english": [
      "Maile Flanagan",
      "Stephanie Sheh",
      "Jeannie Elias",
      "Mary Elizabeth McGlynn",
      "Kate Higgins"
    ],
    "japanese": [
      "Junko Takeuchi",
      "Ema Kogure"
    ]
  },
  "personal": {
    "birthDate": "October 10",
    "sex": "Male",
    "age": [
      "Part I: 12–13",
      "Part II: 15–17"
    ],
    "status": "Alive",
    "height": [
      "Part I: 145.3 cm–147.5 cm",
      "Part II: 166 cm",
      "Blank Period: 180 cm"
    ],
    "weight": [
      "Part I: 40.1 kg–40.6 kg",
      "Part II: 50.9 kg"
    ],
    "bloodType": "B",
    "kekkeiGenkai": [
      "Lava Release",
      "Magnet Release",
      "Boil Release"
    ],
    "classification": [
      "Jinchūriki",
      "Sage",
      "Sensor Type"
    ],
    "tailedBeast": [
      "Shukaku",
      "Matatabi",
      "Isobu",
      "Son Gokū",
      "Kokuō",
      "Saiken",
      "Chōmei",
      "Gyūki",
      "Kurama",
      "Forms"
    ],
    "occupation": [
      "Hokage"
    ],
    "affiliation": [
      "Konohagakure",
      "Mount Myōboku",
      "Allied Shinobi Forces"
    ],
    "team": [
      "Team Kakashi",
      "Sasuke Recovery Team",
      "Konoha 11",
      "Bikōchū Search Team",
      "Kaima Capture Team",
      "Star Guard Team",
      "Peddlers Escort Team",
      "Kazekage Rescue Team",
      "Team One",
      "Eight Man Squad",
      "Hanabi Rescue Team"
    ],
    "clan": [
      "Uzumaki Clan"
    ]
  },
  "charRank": {
    "ninjaRank": [
      "Part I: Genin",
      "Gaiden: Kage"
    ],
    "ninjaRegistration": "012607",
    "academyGradAge": 12
  },
  "family": [
    "Minato Namikaze",
    "Kushina Uzumaki",
    "Boruto Uzumaki",
    "Himawari Uzumaki",
    "Hinata Uzumaki",
    "Jiraiya"
  ],
  "natureTypes": [
    "Wind Release",
    "Lightning Release",
    "Earth Release",
    "Water Release",
    "Fire Release",
    "Lava Release",
    "Magnet Release",
    "Boil Release",
    "Yin Release",
    "Yang Release",
    "Yin–Yang Release"
  ],
  "jutsus": [
    "All Directions Shuriken",
    "Baryon Mode",
    "Big Ball Rasengan",
    "Big Ball Rasenshuriken",
    "Big Ball Spiralling Serial Zone Spheres",
    "Boil Release: Unrivalled Strength",
    "Chakra Transfer Technique",
    "Clone Body Blow",
    "Clone Spinning Heel Drop",
    "Combination Transformation",
    "Continuous Tailed Beast Balls",
    "Earth Release: Earth-Style Wall",
    "Erupting Propulsion Fist",
    "Fire Release: Toad Oil Flame Bullet",
    "Frog Kata",
    "Frog Strike",
    "Gentle Step Spiralling Twin Lion Fists",
    "Harem Technique",
    "Hurricane Thunderclap — Majestic Attire Sword Stroke",
    "Ink Creation",
    "Kurama Arm Attack",
    "Mini-Rasenshuriken",
    "Multiple Shadow Clone Technique",
    "Naruto Region Combo",
    "Naruto Uzumaki Combo",
    "Naruto Uzumaki Two Thousand Combo",
    "Negative Emotions Sensing",
    "New Sexy Technique",
    "Nine-Tails Chakra Mode",
    "Ninja Art: Bare-Handed Blade Block",
    "One Thousand Years of Death",
    "Pachinko Technique",
    "Parachute",
    "Parent and Child Rasengan",
    "Planetary Rasengan",
    "Rasengan",
    "Rasengan: Flash",
    "Regeneration Ability",
    "Runt Ball Rasengan",
    "Sage Art: Lava Release Rasenshuriken",
    "Sage Art: Magnet Release Rasengan",
    "Sage Art: Many Ultra-Big Ball Spiralling Serial Spheres",
    "Sage Art: Super Tailed Beast Rasenshuriken",
    "Sage Mode",
    "Scorch Release: Halo Hurricane Jet Black Arrow Style Zero",
    "Sexy Reverse Harem Technique",
    "Sexy Technique",
    "Sexy Technique: Pole Dance and Nice Body",
    "Shadow Clone Technique",
    "Shadow Shuriken Technique",
    "Shuriken Shadow Clone Technique",
    "Six Paths Sage Mode",
    "Six Paths Senjutsu",
    "Six Paths Yang Power",
    "Six Paths — Chibaku Tensei",
    "Six Paths: Ultra-Big Ball Rasenshuriken",
    "Soap Bubble Ninjutsu",
    "Spiralling Absorption Sphere",
    "Spiralling Serial Spheres",
    "Spiralling Strife Spheres",
    "Strong Fist",
    "Summoning Technique",
    "Toad",
    "Summoning: Food Cart Destroyer Technique",
    "Super Mini-Tailed Beast Ball",
    "Super-Ultra-Big Ball Rasengan",
    "Tailed Beast Ball",
    "Tailed Beast Ball Rasenshuriken",
    "Tailed Beast Chakra Arms",
    "Tailed Beast Full Charge",
    "Tailed Beast Rasengan",
    "Tailed Beast Shockwave",
    "Torii Seal",
    "Truth-Seeking Ball",
    "Turning into a Frog Technique",
    "Typhoon Water Vortex Technique",
    "Ultra-Big Ball Rasengan",
    "Ultra-Many Spiralling Serial Spheres",
    "Uzumaki Formation",
    "Wind Release: Rasengan",
    "Wind Release: Rasenshuriken",
    "Wind Release: Repeated Rasenshuriken",
    "Wind Release: Toad Gun",
    "Wind Release: Toad Oil Bullet",
    "Wind Release: Toad Oil Flame Bullet",
    "Wind Release: Ultra-Big Ball Rasenshuriken",
    "Wisdom Wolf Decay"
  ],
  "tools": [
    "Absorbing Hand",
    "Chakra Blade",
    "Flying Thunder God Kunai",
    "Fūma Shuriken",
    "Hidden Kunai Mechanism",
    "Sand",
    "Scroll of Seals",
    "Shadow Clone Summoning Scroll"
  ],
  "databooks": [
    {
      "name": "Rin no Sho",
      "edition": "First",
      "stats": {
        "ninjutsu": 2,
        "taijutsu": 1.5,
        "genjutsu": 1,
        "intelligence": 1,
        "strength": 2,
        "speed": 2,
        "stamina": 4,
        "handSeals": 1,
        "total": 14.5
      }
    },
    {
      "name": "Tō no Sho",
      "edition": "Second",
      "stats": {
        "ninjutsu": 3,
        "taijutsu": 2,
        "genjutsu": 1,
        "intelligence": 1.5,
        "strength": 3,
        "speed": 3,
        "stamina": 4,
        "handSeals": 1,
        "total": 18.5
      }
    },
    {
      "name": "Sha no Sho",
      "edition": "Third",
      "stats": {
        "ninjutsu": 4,
        "taijutsu": 3.5,
        "genjutsu": 2,
        "intelligence": 3,
        "strength": 3.5,
        "speed": 3.5,
        "stamina": 5,
        "handSeals": 1.5,
        "total": 26
      }
    }
  ]
}
```
