<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>

    <xsl:key name="flowers-by-soil" match="FlowerItem" use="Soil"/>

    <xsl:template match="/">
        <Flowers>
            <xsl:for-each select="//FlowerItem[count(. | key('flowers-by-soil', Soil)[1]) = 1]">
                <xsl:sort select="Soil"/>
                <SoilGroup>
                    <xsl:attribute name="soil">
                        <xsl:value-of select="Soil"/>
                    </xsl:attribute>
                    <xsl:for-each select="key('flowers-by-soil', Soil)">
                        <xsl:copy-of select="."/>
                    </xsl:for-each>
                </SoilGroup>
            </xsl:for-each>
        </Flowers>
    </xsl:template>
</xsl:stylesheet>