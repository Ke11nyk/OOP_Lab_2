<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>

    <xsl:key name="flowers-by-origin" match="FlowerItem" use="Origin"/>

    <xsl:template match="/">
        <Flowers>
            <xsl:for-each select="//FlowerItem[count(. | key('flowers-by-origin', Origin)[1]) = 1]">
                <xsl:sort select="Origin"/>
                <OriginGroup>
                    <xsl:attribute name="origin">
                        <xsl:value-of select="Origin"/>
                    </xsl:attribute>
                    <xsl:for-each select="key('flowers-by-origin', Origin)">
                        <xsl:copy-of select="."/>
                    </xsl:for-each>
                </OriginGroup>
            </xsl:for-each>
        </Flowers>
    </xsl:template>
</xsl:stylesheet>