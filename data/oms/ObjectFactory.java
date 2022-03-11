
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.rt.oms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SubmitOrderRequest_QNAME = new QName("http://oms.rt.ru/", "submitOrderRequest");
    private final static QName _CancelOrderResponse_QNAME = new QName("http://oms.rt.ru/", "cancelOrderResponse");
    private final static QName _QueryOrderResponse_QNAME = new QName("http://oms.rt.ru/", "queryOrderResponse");
    private final static QName _CancelOrderRequest_QNAME = new QName("http://oms.rt.ru/", "cancelOrderRequest");
    private final static QName _AmendOrderResponse_QNAME = new QName("http://oms.rt.ru/", "amendOrderResponse");
    private final static QName _QueryOrderRequest_QNAME = new QName("http://oms.rt.ru/", "queryOrderRequest");
    private final static QName _Fault_QNAME = new QName("http://oms.rt.ru/", "Fault");
    private final static QName _AmendOrderRequest_QNAME = new QName("http://oms.rt.ru/", "amendOrderRequest");
    private final static QName _SubmitOrderResponse_QNAME = new QName("http://oms.rt.ru/", "submitOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.rt.oms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubmitOrderRequest }
     * 
     */
    public SubmitOrderRequest createSubmitOrderRequest() {
        return new SubmitOrderRequest();
    }

    /**
     * Create an instance of {@link CancelOrderResponse }
     * 
     */
    public CancelOrderResponse createCancelOrderResponse() {
        return new CancelOrderResponse();
    }

    /**
     * Create an instance of {@link QueryOrderResponse }
     * 
     */
    public QueryOrderResponse createQueryOrderResponse() {
        return new QueryOrderResponse();
    }

    /**
     * Create an instance of {@link CancelOrderRequest }
     * 
     */
    public CancelOrderRequest createCancelOrderRequest() {
        return new CancelOrderRequest();
    }

    /**
     * Create an instance of {@link AmendOrderResponse }
     * 
     */
    public AmendOrderResponse createAmendOrderResponse() {
        return new AmendOrderResponse();
    }

    /**
     * Create an instance of {@link QueryOrderRequest }
     * 
     */
    public QueryOrderRequest createQueryOrderRequest() {
        return new QueryOrderRequest();
    }

    /**
     * Create an instance of {@link TFault }
     * 
     */
    public TFault createTFault() {
        return new TFault();
    }

    /**
     * Create an instance of {@link AmendOrderRequest }
     * 
     */
    public AmendOrderRequest createAmendOrderRequest() {
        return new AmendOrderRequest();
    }

    /**
     * Create an instance of {@link SubmitOrderResponse }
     * 
     */
    public SubmitOrderResponse createSubmitOrderResponse() {
        return new SubmitOrderResponse();
    }

    /**
     * Create an instance of {@link QueryRequestOrder }
     * 
     */
    public QueryRequestOrder createQueryRequestOrder() {
        return new QueryRequestOrder();
    }

    /**
     * Create an instance of {@link CancelResponseOrder }
     * 
     */
    public CancelResponseOrder createCancelResponseOrder() {
        return new CancelResponseOrder();
    }

    /**
     * Create an instance of {@link Attributes }
     * 
     */
    public Attributes createAttributes() {
        return new Attributes();
    }

    /**
     * Create an instance of {@link Attachment }
     * 
     */
    public Attachment createAttachment() {
        return new Attachment();
    }

    /**
     * Create an instance of {@link InheritableAttribute }
     * 
     */
    public InheritableAttribute createInheritableAttribute() {
        return new InheritableAttribute();
    }

    /**
     * Create an instance of {@link OrderNotification }
     * 
     */
    public OrderNotification createOrderNotification() {
        return new OrderNotification();
    }

    /**
     * Create an instance of {@link SubmitResponseOrder }
     * 
     */
    public SubmitResponseOrder createSubmitResponseOrder() {
        return new SubmitResponseOrder();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link RequestOrderItem }
     * 
     */
    public RequestOrderItem createRequestOrderItem() {
        return new RequestOrderItem();
    }

    /**
     * Create an instance of {@link ResponseOrderTask }
     * 
     */
    public ResponseOrderTask createResponseOrderTask() {
        return new ResponseOrderTask();
    }

    /**
     * Create an instance of {@link RequestOrderItems }
     * 
     */
    public RequestOrderItems createRequestOrderItems() {
        return new RequestOrderItems();
    }

    /**
     * Create an instance of {@link ResponseOrderTasks }
     * 
     */
    public ResponseOrderTasks createResponseOrderTasks() {
        return new ResponseOrderTasks();
    }

    /**
     * Create an instance of {@link ResponseOrderItems }
     * 
     */
    public ResponseOrderItems createResponseOrderItems() {
        return new ResponseOrderItems();
    }

    /**
     * Create an instance of {@link AsyncSubmitResponseOrder }
     * 
     */
    public AsyncSubmitResponseOrder createAsyncSubmitResponseOrder() {
        return new AsyncSubmitResponseOrder();
    }

    /**
     * Create an instance of {@link OrderNotifications }
     * 
     */
    public OrderNotifications createOrderNotifications() {
        return new OrderNotifications();
    }

    /**
     * Create an instance of {@link OrderItemBillingInfo }
     * 
     */
    public OrderItemBillingInfo createOrderItemBillingInfo() {
        return new OrderItemBillingInfo();
    }

    /**
     * Create an instance of {@link QueryOrderItems }
     * 
     */
    public QueryOrderItems createQueryOrderItems() {
        return new QueryOrderItems();
    }

    /**
     * Create an instance of {@link AmendResponseOrder }
     * 
     */
    public AmendResponseOrder createAmendResponseOrder() {
        return new AmendResponseOrder();
    }

    /**
     * Create an instance of {@link OIReference }
     * 
     */
    public OIReference createOIReference() {
        return new OIReference();
    }

    /**
     * Create an instance of {@link OrderItemResult }
     * 
     */
    public OrderItemResult createOrderItemResult() {
        return new OrderItemResult();
    }

    /**
     * Create an instance of {@link Attribute }
     * 
     */
    public Attribute createAttribute() {
        return new Attribute();
    }

    /**
     * Create an instance of {@link SubmitRequestOrder }
     * 
     */
    public SubmitRequestOrder createSubmitRequestOrder() {
        return new SubmitRequestOrder();
    }

    /**
     * Create an instance of {@link CancelRequestOrder }
     * 
     */
    public CancelRequestOrder createCancelRequestOrder() {
        return new CancelRequestOrder();
    }

    /**
     * Create an instance of {@link QueryResponseOrder }
     * 
     */
    public QueryResponseOrder createQueryResponseOrder() {
        return new QueryResponseOrder();
    }

    /**
     * Create an instance of {@link SyncSubmitResponseOrder }
     * 
     */
    public SyncSubmitResponseOrder createSyncSubmitResponseOrder() {
        return new SyncSubmitResponseOrder();
    }

    /**
     * Create an instance of {@link AmendRequestOrder }
     * 
     */
    public AmendRequestOrder createAmendRequestOrder() {
        return new AmendRequestOrder();
    }

    /**
     * Create an instance of {@link Party }
     * 
     */
    public Party createParty() {
        return new Party();
    }

    /**
     * Create an instance of {@link InheritableAttributes }
     * 
     */
    public InheritableAttributes createInheritableAttributes() {
        return new InheritableAttributes();
    }

    /**
     * Create an instance of {@link ResponseOrderItem }
     * 
     */
    public ResponseOrderItem createResponseOrderItem() {
        return new ResponseOrderItem();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link OrderParties }
     * 
     */
    public OrderParties createOrderParties() {
        return new OrderParties();
    }

    /**
     * Create an instance of {@link OIReferences }
     * 
     */
    public OIReferences createOIReferences() {
        return new OIReferences();
    }

    /**
     * Create an instance of {@link Comments }
     * 
     */
    public Comments createComments() {
        return new Comments();
    }

    /**
     * Create an instance of {@link QueryOrderItem }
     * 
     */
    public QueryOrderItem createQueryOrderItem() {
        return new QueryOrderItem();
    }

    /**
     * Create an instance of {@link NotificationResponse }
     * 
     */
    public NotificationResponse createNotificationResponse() {
        return new NotificationResponse();
    }

    /**
     * Create an instance of {@link OrderItemSpecification }
     * 
     */
    public OrderItemSpecification createOrderItemSpecification() {
        return new OrderItemSpecification();
    }

    /**
     * Create an instance of {@link OrderResult }
     * 
     */
    public OrderResult createOrderResult() {
        return new OrderResult();
    }

    /**
     * Create an instance of {@link EquipmentInfo }
     * 
     */
    public EquipmentInfo createEquipmentInfo() {
        return new EquipmentInfo();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "submitOrderRequest")
    public JAXBElement<SubmitOrderRequest> createSubmitOrderRequest(SubmitOrderRequest value) {
        return new JAXBElement<SubmitOrderRequest>(_SubmitOrderRequest_QNAME, SubmitOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "cancelOrderResponse")
    public JAXBElement<CancelOrderResponse> createCancelOrderResponse(CancelOrderResponse value) {
        return new JAXBElement<CancelOrderResponse>(_CancelOrderResponse_QNAME, CancelOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "queryOrderResponse")
    public JAXBElement<QueryOrderResponse> createQueryOrderResponse(QueryOrderResponse value) {
        return new JAXBElement<QueryOrderResponse>(_QueryOrderResponse_QNAME, QueryOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "cancelOrderRequest")
    public JAXBElement<CancelOrderRequest> createCancelOrderRequest(CancelOrderRequest value) {
        return new JAXBElement<CancelOrderRequest>(_CancelOrderRequest_QNAME, CancelOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AmendOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "amendOrderResponse")
    public JAXBElement<AmendOrderResponse> createAmendOrderResponse(AmendOrderResponse value) {
        return new JAXBElement<AmendOrderResponse>(_AmendOrderResponse_QNAME, AmendOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "queryOrderRequest")
    public JAXBElement<QueryOrderRequest> createQueryOrderRequest(QueryOrderRequest value) {
        return new JAXBElement<QueryOrderRequest>(_QueryOrderRequest_QNAME, QueryOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "Fault")
    public JAXBElement<TFault> createFault(TFault value) {
        return new JAXBElement<TFault>(_Fault_QNAME, TFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AmendOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "amendOrderRequest")
    public JAXBElement<AmendOrderRequest> createAmendOrderRequest(AmendOrderRequest value) {
        return new JAXBElement<AmendOrderRequest>(_AmendOrderRequest_QNAME, AmendOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oms.rt.ru/", name = "submitOrderResponse")
    public JAXBElement<SubmitOrderResponse> createSubmitOrderResponse(SubmitOrderResponse value) {
        return new JAXBElement<SubmitOrderResponse>(_SubmitOrderResponse_QNAME, SubmitOrderResponse.class, null, value);
    }

}
