<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Athlete Decathlon Result</h2>
                <table border="1">

                    <tr bgcolor="#9acd32">
                        <th style="text-align:left">Athlete</th>
                        <th style="text-align:left">Place</th>
                        <th style="text-align:left">Total Score</th>
                        <th style="text-align:left">track_100m</th>
                        <th style="text-align:left">field_long_jump</th>
                        <th style="text-align:left">field_shot_put</th>
                        <th style="text-align:left">field_high_jump</th>
                        <th style="text-align:left">track_run_400m</th>
                        <th style="text-align:left">track_110m_hurdles</th>
                        <th style="text-align:left">field_discus_throw</th>
                        <th style="text-align:left">field_pole_vault</th>
                        <th style="text-align:left">field_javelin_throw</th>
                        <th style="text-align:left">track_run_1500m</th>
                    </tr>

                    <xsl:for-each select="athletes/athlete">
                        <tr>
                            <td>
                                <xsl:value-of select="@name"/>
                            </td>
                            <td>
                                <xsl:value-of select="@place"/>
                            </td>
                            <td>
                                <xsl:value-of select="@totalScore"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/track_100m"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/field_long_jump"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/field_shot_put"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/field_high_jump"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/track_run_400m"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/track_110m_hurdles"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/field_discus_throw"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/field_pole_vault"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/field_javelin_throw"/>
                            </td>
                            <td>
                                <xsl:value-of select="events/track_run_1500m"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

