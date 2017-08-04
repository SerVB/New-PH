<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "${project.licensePath}">

<#if package?? && package != "">
package ${package};

</#if>
/**
 *
 * @author ${user}
 */
public final class ${name} {

    /**
     * Prevents from creating an instance of the class.
     */
    private ${name}() {}

}
