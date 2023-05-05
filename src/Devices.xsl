<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    <xsl:key name="kSuite" match="origin" use="."/>

    <xsl:template match="/*">
        <xsl:copy>
            <xsl:apply-templates
                    select="device/origin[generate-id() =
                                                 generate-id(key('kSuite', .)[1])]"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="origin">
        <origin name="{.}">
            <xsl:apply-templates select="key('kSuite', .)/.."/>

        </origin>
    </xsl:template>

    <xsl:template match="device">

        <xsl:value-of select="@name"/>

        <xsl:copy-of select="./node()[local-name() != 'origin']"/>
        <id>
            <xsl:value-of select="@id"/>
        </id>
    </xsl:template>
</xsl:stylesheet>