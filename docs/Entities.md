## Entities config

Use the [Paper 26.2 entity names](https://jd.papermc.io/paper/26.2/org/bukkit/entity/EntityType.html) as top-level keys.

```yaml
PLAYER:
    '*':
        Money:
            Enabled: true
            Chance: 100%
            Value: 100 ? 200
            Division-method: 'LAST_HIT'
            Permission: 'km.custom.permission'
            Limit: 2000
        Custom-commands:
            Enabled: true
            Run-method: 'ALL'
            Commands:
                '1':
                    Command: 'say {player} killed an entity'
                    Chance: 12.5%
                    Permission: 'km.custom.permission'
                    Limit: 10
                '2':
                    Command: 'give {player} DIAMOND 5'
                    Chance: 0.5%
                    Permission: 'km.custom.permission'
                    Limit: 15
        Custom-item-drop:
            Enabled: true
            Keep-default-items: false
            Run-method: 'ALL'
            Items:
                '1':
                    ItemStack:
                        ==: org.bukkit.inventory.ItemStack
                        schema_version: 1
                        DataVersion: 4903
                        id: minecraft:short_grass
                        count: 1
                    Random-amount: 1 ? 20
                    Chance: 100%
                    Permission: 'km.custom.permission'
                    Limit: 200
                '2':
                    ItemStack:
                        ==: org.bukkit.inventory.ItemStack
                        schema_version: 1
                        DataVersion: 4903
                        id: minecraft:diamond
                        count: 32
                    Chance: 80%
                    Permission: 'km.custom.permission'
                    Limit: 200
        Custom-exp-drop:
                Enabled: true
                Chance: 100%
                Value: 100 ? 200
                Permission: 'km.custom.permission'
                Limit: 1000
        Cash-transfer:
            Enabled: true
            Percent: 25%
            Max-amount: 5000
            Chance: 100%
            Division-method: 'LAST_HIT'
            Permission: 'km.custom.permission'
            Limit: 10000
```

## Configuration manual

```yaml
PLAYER:
```

This is the entity name. The list depend from server version. [Check latest spigot list here](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/EntityType.html)

```yaml
'*':
```

The second row the world name. The '*' mean that the rules valid on all worlds. You can change it to world name or you can add more worlds with commas.

Example: **'world, world_nether'**

> You can add more rewards accordings worlds:

```yaml
PLAYER:
    'world':
        Money:
            Enabled: true
            Chance: 75%
            Value: 100
    'world_nether, world_the_end':
        Money:
            Enabled: true
            Chance: 100%
            Value: 200
    'mine':
        Money:
            Enabled: true
            Chance: 5%
            Value: 1500
```

---
> Money reward section
```yaml
Money:
    Enabled: true
    Chance: 100%
    Value: 100 ? 200
    Division-method: 'LAST_HIT'
    Permission: 'km.custom.permission'
    Limit: 2000
```

+ **Enabled:** You can enable or disable it. Accepted values: true or false _<optional, default value is true>_
+ **Chance:** Reward chance. Min: 1%, max: 100% _<optional, default value is 100%>_
+ **Value:** The money amount. You can set random money between two numbers, or you can set up fix amount.
+ Example for random: **Value: 100 ? 200**
+ Example for fix: **Value: 100**

You can set negative numbers too: **Value: -50 ? 50**

+ **Division-method:** Acceptable values: "LAST_HIT" or "SHARED". If you choose SHARED, then the reward will divide between entity damagers. LAST_HIT will reward only the killer (last damager) with full amount of reward. _<optional, default value is LAST_HIT>_
+ **Permission:** You can set custom permission. If player hasn't this perm, then player will not get reward. _<optional, there isn't default value>_
+ **Limit:** If players reached limit with money earning, then players will not get reward while server restart or limit reset. You can set limit reset time in **config.yml** and you can reset it manually with **/kmadmin limit reset** command. _<optional, there isn't default value>_

---

> You can execute commands when players kill entities.

```yaml
Custom-commands:
    Enabled: true
    Run-method: 'ALL'
    Commands:
        '1':
            Command: 'say {player} killed an entity'
            Chance: 12.5%
            Permission: 'km.custom.permission'
            Limit: 10
        '2':
            Command: 'give {player} DIAMOND 5'
            Chance: 0.5%
            Permission: 'km.custom.permission'
            Limit: 15
```

+ **Enabled:** You can enable or disable it. Accepted values: true or false _<optional, default value is true>_
+ **Run-method:** Acceptable values: "ALL" or "RANDOM". If you choose "ALL", then all command will execute. If random, then only one. _<optional, default value is ALL>_
+ **"Number:"** If you add more commands, you must increment the number. Same number is not allowed.
+ **Command:** That command which you want execute. The {player} will be replaced with killer's name.
+ **Chance:** Command chance. Min: 1%, max: 100% _<optional, default value is 100%>_
+ **Permission:** You can set custom permission. If player hasn't this perm, then the command will not be execute. _<optional, there isn't default value>_
+ **Limit:** If players reached limit with money earning, then players will not get reward while server restart or limit reset. You can set limit reset time in **config.yml** and you can reset it manually with **/kmadmin limit reset** command. _<optional, there isn't default value>_

```yaml
Custom-item-drop:
    Enabled: true
    Keep-default-items: false
    Run-method: 'ALL'
    Items:
        '1':
            ItemStack:
                ==: org.bukkit.inventory.ItemStack
                schema_version: 1
                DataVersion: 4903
                id: minecraft:short_grass
                count: 1
            Random-amount: 1 ? 20
            Chance: 100%
            Permission: 'km.custom.permission'
            Limit: 200
        '2':
            ItemStack:
                ==: org.bukkit.inventory.ItemStack
                schema_version: 1
                DataVersion: 4903
                id: minecraft:diamond
                count: 32
            Chance: 80%
            Permission: 'km.custom.permission'
            Limit: 200
```
---
Set custom item drop

+ **Enabled:** You can enable or disable it. Accepted values: true or false _<optional, default value is true>_
+ **Keep-default-items:** If you set it true, then will be drop the default items (leather, food or other) too. _<optional, default value is true>_
+ **Run-method:** Acceptable values: "ALL" or "RANDOM". If you choose "ALL", then all command will execute. If random, then only one. _<optional, default value is ALL>_
+ **"Number:"** If you add more commands, you must increment the number. Same number is not allowed.

+ **ItemStack:** Use Paper's serialized item map with `schema_version: 1`, `DataVersion: 4903`, an item `id` and a `count`.
+ **Random-amount:** Set random amount for ItemStack. If you don't want random amount, just remove it. _<optional, there isn't default value>_
+ **Chance:** Command chance. Min: 1%, max: 100% _<optional, default value is 100%>_
+ **Permission:** You can set custom permission. If player hasn't this perm, then the command will not be execute. _<optional, there isn't default value>_
+ **Limit:** If players reached limit with money earning, then players will not get reward while server restart or limit reset. You can set limit reset time in **config.yml** and you can reset it manually with **/kmadmin limit reset** command. _<optional, there isn't default value>_

### ItemStack examples

A sheep spawn egg.

```yaml
ItemStack:
    ==: org.bukkit.inventory.ItemStack
    schema_version: 1
    DataVersion: 4903
    id: minecraft:sheep_spawn_egg
    count: 1
```

An iron sword with enchantments.

```yaml
ItemStack:
    ==: org.bukkit.inventory.ItemStack
    schema_version: 1
    DataVersion: 4903
    id: minecraft:iron_sword
    count: 1
    components:
        minecraft:enchantments: '{"minecraft:knockback":1,"minecraft:fire_aspect":1}'
```
---
> You can set custom experience point drop
```yaml
Custom-exp-drop:
    Enabled: true
    Chance: 100%
    Value: 100 ? 200
    Permission: 'km.custom.permission'
    Limit: 1000
```

+ **Enabled:** You can enable or disable it. Accepted values: true or false <optional, default value is true>
+ **Chance:** Reward chance. Min: 1%, max: 100% <optional, default value is 100%>
+ **Value:** The money amount. You can set random money between two numbers, or you can set up fix amount.
Example for random: Value: 100 ? 200
Example for fix: Value: 100
+ **Permission:** You can set custom permission. If player hasn't this perm, then player will not get reward. <optional, there isn't default value>
+ **Limit:** If players reached limit with exp earning, then players will not get reward while server restart or limit reset. You can set limit reset time in config.yml and you can reset it manually with /kmadmin limit reset command. <optional, there isn't default value>
---
> Transfer money between killer (earn) and victim (lose). You can only use this in PLAYER entity section.
```yaml
Cash-transfer:
    Enabled: true
    Percent: 25%
    Max-amount: 5000
    Chance: 100%
    Division-method: 'LAST_HIT'
    Permission: 'km.custom.permission'
    Limit: 10000
```

+ **Enabled:** You can enable or disable it. Accepted values: true or false _<optional, default value is true>_
+ **Percent:** Percent of victim's money which killer will earn. Min: 1%, max: 100%
+ **Max-amount:** Maximum earning limit in one kill. _<optional, there isn't default value>_
+ **Chance:** Transfer chance. Min: 1%, max: 100% _<optional, default value is 100%>_
+ **Division-method:** Acceptable values: "LAST_HIT" or "SHARED". If you choose SHARED, then the reward will divide between entity damagers. LAST_HIT will reward only the killer (last damager) with full amount of reward. _<optional, default value is LAST_HIT>_
+ **Limit:** If players reached limit with money earning, then players will not get reward while server restart or limit reset. You can set limit reset time in **config.yml** and you can reset it manually with **/kmadmin limit reset** command. _<optional, there isn't default value>_
