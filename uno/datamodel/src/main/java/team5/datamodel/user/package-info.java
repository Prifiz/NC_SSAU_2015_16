
@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=LocalDate.class, 
        value=LocalDateAdapter.class),
   
})
package team5.datamodel.user;
 
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import java.time.LocalDate;
import team5.datamodel.transmissions.LocalDateAdapter;
