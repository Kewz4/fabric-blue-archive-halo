Pure client-side mod that also fixes the duplicate rendering bug of beacon beams.


# Display Rules:
### Level
The halo style changes with the beacon level. You can use colored glass to dye it. The level does not necessarily equal the number of rings.
### Level Decay
The halo size and ring count increase with the beacon level. To control the actual number of rings displayed, the level is reduced by the number of consecutive **colorless transparent glass/panes** directly above the beacon.

### Dyeing
Starting from the last transparent glass block mentioned above, each block of **beacon beam color** upward is applied from the outer ring to the inner ring in sequence.

The beacon beam color is used instead of the glass block's own color to prevent jarring color transitions.
