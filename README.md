# NoDropPlus (1.9)

### Features
 * Prevent Players from dropping items
 * Have players keep certain items on death


### Installation
* Drop NoDropPlus.jar in to your Plugins folder
* Restart/Reload your server
* Configure items/message


## Default Configuration File

    # Configuration for NoDropPlus by Gh0stByte of Gh0stByte.ga.
    # Enabled - Enable/Disable NoDrop. Includes keeping on death.
    # Message - Message when you try to drop a NoDrop item. (You can use & or § for chat colors)
    # KeepOnDeath - When you die, you keep the configured Items.
    # Items - Items not allowed to drop, and that you keep on death.

    NoDropPlus:
      Enabled: true
      KeepOnDeath: true
      Message: "&7[&3NoDropPlus&7] &c&lHey! &3You aren't allowed to drop that item."
      Items:
      - DIAMOND
      - DIAMOND_PICKAXE


## Permissions 
  * ```nodropplus.bypass``` - Bypass NoDropPlus and drop blocked items
  * ```nodropplus.reload``` - Reload the configuration


## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D


## History

### 1.0
* Initial Release 


## Credits

Made by [Gh0stByte](http://twitter.com/Gh0stByte)

## License

This project is licensed under GNU General Public License v3.0. See [License](/blob/master/LICENSE)